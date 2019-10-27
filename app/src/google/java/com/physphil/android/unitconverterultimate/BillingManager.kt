package com.physphil.android.unitconverterultimate

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import com.physphil.android.unitconverterultimate.models.Donation

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
            setSkusList(Donation.allProductIds)
            setType(BillingClient.SkuType.INAPP)
        }
        billingClient.querySkuDetailsAsync(params.build()) { result: BillingResult, donations: List<SkuDetails> ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                listener.onComplete(QueryDonationsResult.Success(donations))
            } else {
                listener.onComplete(QueryDonationsResult.Error(result.debugMessage))
            }
        }
    }

    fun donate(activity: Activity, donation: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(donation)
            .build()
        val responseCode = billingClient.launchBillingFlow(activity, flowParams)
        // TODO deal with response code... close if not OK?
        // TODO add callback for activity to close after donation
    }

    // region PurchasesUpdatedListener impl
    override fun onPurchasesUpdated(
        billingResult: BillingResult?,
        purchases: MutableList<Purchase>?
    ) {
        // TODO get purchase when user donates
        Log.d("phil", "On purchases updated")
        Log.d("phil", "Billing result = $billingResult")
        Log.d("phil", "purchases = $purchases")
    }
    // endregion
}

sealed class QueryDonationsResult {
    data class Success(val donations: List<SkuDetails>) : QueryDonationsResult()
    data class Error(val message: String) : QueryDonationsResult()
}