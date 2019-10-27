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
        val params = SkuDetailsParams.newBuilder().apply {
            setSkusList(Donation.all)
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