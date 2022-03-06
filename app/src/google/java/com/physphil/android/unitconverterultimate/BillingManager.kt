package com.physphil.android.unitconverterultimate

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams

class BillingManager : PurchasesUpdatedListener {

    interface ConnectionStateListener {
        fun onConnected()
        fun onConnectionError(message: String)
        fun onDisconnected()
    }

    interface QueryDonationsListener {
        fun onComplete(result: QueryDonationsResult)
    }

    interface DonationResultListener {
        fun onDonationSuccess()
        fun onDonationFailed(message: String? = null)
        fun onUserCanceled()
    }

    private lateinit var billingClient: BillingClient
    private var connectionStateListener: ConnectionStateListener? = null
    private var donationResultListener: DonationResultListener? = null

    fun connect(context: Context, listener: ConnectionStateListener) {
        connectionStateListener = listener
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
        connectionStateListener = null
        donationResultListener = null
        billingClient.endConnection()
    }

    fun queryDonationOptions(listener: QueryDonationsListener) {
        val params = SkuDetailsParams.newBuilder()
            .setSkusList(DonationProductIdProvider.all)
            .setType(BillingClient.SkuType.INAPP)
            .build()
        billingClient.querySkuDetailsAsync(params) { result: BillingResult, donations: List<SkuDetails>? ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                donations?.let {
                    listener.onComplete(QueryDonationsResult.Success(donations))
                } ?: listener.onComplete(QueryDonationsResult.Error("No donations found"))
            } else {
                listener.onComplete(QueryDonationsResult.Error(result.debugMessage))
            }
        }
    }

    fun donate(activity: Activity, donation: SkuDetails, listener: DonationResultListener) {
        donationResultListener = listener
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(donation)
            .build()
        val result = billingClient.launchBillingFlow(activity, flowParams)
        if (result.responseCode != BillingClient.BillingResponseCode.OK) {
            connectionStateListener?.onDisconnected()
        }
    }

    // region PurchasesUpdatedListener impl
    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        // Handle purchase after successful user donation
        when (billingResult?.responseCode) {
            BillingClient.BillingResponseCode.OK -> purchases?.handle() ?: donationResultListener?.onDonationSuccess()
            BillingClient.BillingResponseCode.USER_CANCELED -> donationResultListener?.onUserCanceled()
            else -> donationResultListener?.onDonationFailed(billingResult?.debugMessage)
        }
    }
    // endregion

    private fun List<Purchase>.handle() {
        // Consume purchase and succeed on completion
        val params = ConsumeParams.newBuilder()
            .setPurchaseToken(first().purchaseToken)
            .build()
        billingClient.consumeAsync(params) { _, _ ->
            donationResultListener?.onDonationSuccess()
        }
    }
}

sealed class QueryDonationsResult {
    data class Success(val donations: List<SkuDetails>) : QueryDonationsResult()
    data class Error(val message: String) : QueryDonationsResult()
}