package com.physphil.android.unitconverterultimate.data.network.model

import com.google.gson.annotations.SerializedName
import com.physphil.android.unitconverterultimate.models.Currency
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity

data class CurrencyResponse(val rates: Rates)

data class Rates(
    @SerializedName("AUD") val aud: String,
    @SerializedName("BGN") val bgn: String,
    @SerializedName("BRL") val brl: String,
    @SerializedName("CAD") val cad: String,
    @SerializedName("CHF") val chf: String,
    @SerializedName("CNY") val cny: String,
    @SerializedName("CZK") val czk: String,
    @SerializedName("DKK") val dkk: String,
    @SerializedName("GBP") val gbp: String,
    @SerializedName("HKD") val hkd: String,
    @SerializedName("HRK") val hrk: String,
    @SerializedName("HUF") val huf: String,
    @SerializedName("IDR") val idr: String,
    @SerializedName("ILS") val ils: String,
    @SerializedName("INR") val inr: String,
    @SerializedName("ISK") val isk: String,
    @SerializedName("JPY") val jpy: String,
    @SerializedName("KRW") val krw: String,
    @SerializedName("MXN") val mxn: String,
    @SerializedName("MYR") val myr: String,
    @SerializedName("NOK") val nok: String,
    @SerializedName("NZD") val nzd: String,
    @SerializedName("PHP") val php: String,
    @SerializedName("PLN") val pln: String,
    @SerializedName("RON") val ron: String,
    @SerializedName("RUB") val rub: String,
    @SerializedName("SEK") val sek: String,
    @SerializedName("SGD") val sgd: String,
    @SerializedName("THB") val thb: String,
    @SerializedName("TRY") val lira: String,
    @SerializedName("USD") val usd: String,
    @SerializedName("ZAR") val zar: String
)

fun CurrencyResponse.toRateEntities(): List<RateEntity> =
    Currency.all.map {
        RateEntity(
            code = it.code,
            rate = when (it) {
                Currency.AustralianDollar -> rates.aud
                Currency.BulgarianLev -> rates.bgn
                Currency.BrazilianReal -> rates.brl
                Currency.CanadianDollar -> rates.cad
                Currency.SwissFranc -> rates.chf
                Currency.ChineseYuan -> rates.cny
                Currency.CzechKoruna -> rates.czk
                Currency.DanishKrone -> rates.dkk
                Currency.Euro -> "1.0"
                Currency.BritishPound -> rates.gbp
                Currency.HongKongDollar -> rates.hkd
                Currency.CroatianKuna -> rates.hrk
                Currency.HungarianForint -> rates.huf
                Currency.IndonesianRupiah -> rates.idr
                Currency.IsraeliShekel -> rates.ils
                Currency.IndianRupee -> rates.inr
                Currency.IcelandicKrona -> rates.isk
                Currency.JapaneseYen -> rates.jpy
                Currency.KoreanWon -> rates.krw
                Currency.MexicanPeso -> rates.mxn
                Currency.MalaysianRinggit -> rates.myr
                Currency.NorwegianKrone -> rates.nok
                Currency.NewZealandDollar -> rates.nzd
                Currency.PhilippinePeso -> rates.php
                Currency.PolishZloty -> rates.pln
                Currency.RomanianLeu -> rates.ron
                Currency.RussianRouble -> rates.rub
                Currency.SwedishKrona -> rates.sek
                Currency.SingaporeDollar -> rates.sgd
                Currency.ThaiBaht -> rates.thb
                Currency.TurkishLira -> rates.lira
                Currency.AmericanDollar -> rates.usd
                Currency.SouthAfricanRand -> rates.zar
            }
        )
    }