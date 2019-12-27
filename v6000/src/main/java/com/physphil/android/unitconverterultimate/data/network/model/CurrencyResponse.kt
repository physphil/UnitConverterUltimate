package com.physphil.android.unitconverterultimate.data.network.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CurrencyResponse(val rates: Rates)

data class Rates(
    @SerializedName("AUD") val aud: BigDecimal,
    @SerializedName("BGN") val bgn: BigDecimal,
    @SerializedName("BRL") val brl: BigDecimal,
    @SerializedName("CAD") val cad: BigDecimal,
    @SerializedName("CHF") val chf: BigDecimal,
    @SerializedName("CNY") val cny: BigDecimal,
    @SerializedName("CZK") val czk: BigDecimal,
    @SerializedName("DKK") val dkk: BigDecimal,
    @SerializedName("GBP") val gbp: BigDecimal,
    @SerializedName("HKD") val hkd: BigDecimal,
    @SerializedName("HRK") val hrk: BigDecimal,
    @SerializedName("HUF") val huf: BigDecimal,
    @SerializedName("IDR") val idr: BigDecimal,
    @SerializedName("ILS") val ils: BigDecimal,
    @SerializedName("INR") val inr: BigDecimal,
    @SerializedName("ISK") val isk: BigDecimal,
    @SerializedName("JPY") val jpy: BigDecimal,
    @SerializedName("KRW") val krw: BigDecimal,
    @SerializedName("MXN") val mxn: BigDecimal,
    @SerializedName("MYR") val myr: BigDecimal,
    @SerializedName("NOK") val nok: BigDecimal,
    @SerializedName("NZD") val nzd: BigDecimal,
    @SerializedName("PHP") val php: BigDecimal,
    @SerializedName("PLN") val pln: BigDecimal,
    @SerializedName("RON") val ron: BigDecimal,
    @SerializedName("RUB") val rub: BigDecimal,
    @SerializedName("SEK") val sek: BigDecimal,
    @SerializedName("SGD") val sgd: BigDecimal,
    @SerializedName("THB") val thb: BigDecimal,
    @SerializedName("TRY") val lira: BigDecimal,
    @SerializedName("USD") val usd: BigDecimal,
    @SerializedName("ZAR") val zar: BigDecimal
)