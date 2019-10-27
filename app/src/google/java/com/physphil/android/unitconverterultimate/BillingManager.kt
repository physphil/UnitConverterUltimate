package com.physphil.android.unitconverterultimate

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import com.physphil.android.unitconverterultimate.models.Donation
import com.physphil.android.unitconverterultimate.ui.DonationListAdapter

class BillingManager : PurchasesUpdatedListener {

    private object ProductId {
        const val LARGE_DONATION = "donate_large"
        const val MEDIUM_DONATION = "donate_medium"
        const val SMALL_DONATION = "donate_small"
        const val TINY_DONATION = "donate_very_small"
        const val INSANE_DONATION = "donate_insane"
    }

    interface ConnectionStateListener {
        fun onConnected()
        fun onConnectionError(message: String)
        fun onDisconnected()
    }

    interface QueryDonationsListener {
        fun onComplete(result: QueryDonationsResult)
    }

    private lateinit var billingClient: BillingClient

    fun connect(context: Context, listener: ConnectionStateListener) {
        billingClient = BillingClient.newBuilder(context)
            .setListener(this)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                listener.onDisconnected()
            }

            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    listener.onConnected()
                } else {
                    listener.onConnectionError(billingResult.debugMessage)
                }
            }
        })
    }

    fun disconnect() {
        billingClient.endConnection()
    }

    fun queryDonationOptions(listener: QueryDonationsListener) {
        val skuList = arrayListOf(
            ProductId.LARGE_DONATION,
            ProductId.MEDIUM_DONATION,
            ProductId.SMALL_DONATION,
            ProductId.TINY_DONATION,
            ProductId.INSANE_DONATION
        )
        val params = SkuDetailsParams.newBuilder().apply {
            setSkusList(skuList)
            setType(BillingClient.SkuType.INAPP)
        }
        billingClient.querySkuDetailsAsync(params.build()) { result: BillingResult, donations: List<SkuDetails> ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                listener.onComplete(QueryDonationsResult.Success(donations.toDomainModels()))
            } else {
                listener.onComplete(QueryDonationsResult.Error(result.debugMessage))
            }
        }
    }

    // region PurchasesUpdatedListener impl
    override fun onPurchasesUpdated(
        billingResult: BillingResult?,
        purchases: MutableList<Purchase>?
    ) {
        // TODO
    }
    // endregion

    private fun List<SkuDetails>.toDomainModels(): List<Donation> = map {
        Donation(
            productId = it.sku,
            title = it.title,
            description = it.description,
            price = it.price,
            currencyCode = it.priceCurrencyCode
        )
    }
}

sealed class QueryDonationsResult {
    data class Success(val donations: List<Donation>) : QueryDonationsResult()
    data class Error(val message: String) : QueryDonationsResult()
}