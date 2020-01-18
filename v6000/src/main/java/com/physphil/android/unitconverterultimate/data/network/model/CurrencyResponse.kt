package com.physphil.android.unitconverterultimate.data.network.model

import com.google.gson.annotations.SerializedName
import com.physphil.android.unitconverterultimate.models.Currency
import com.physphil.android.unitconverterultimate.models.CurrencyCodes
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity

data class CurrencyResponse(val rates: Rates)

data class Rates(
    @SerializedName(CurrencyCodes.AUD) val aud: String,
    @SerializedName(CurrencyCodes.BGN) val bgn: String,
    @SerializedName(CurrencyCodes.BRL) val brl: String,
    @SerializedName(CurrencyCodes.CAD) val cad: String,
    @SerializedName(CurrencyCodes.CHF) val chf: String,
    @SerializedName(CurrencyCodes.CNY) val cny: String,
    @SerializedName(CurrencyCodes.CZK) val czk: String,
    @SerializedName(CurrencyCodes.DKK) val dkk: String,
    @SerializedName(CurrencyCodes.GBP) val gbp: String,
    @SerializedName(CurrencyCodes.HKD) val hkd: String,
    @SerializedName(CurrencyCodes.HRK) val hrk: String,
    @SerializedName(CurrencyCodes.HUF) val huf: String,
    @SerializedName(CurrencyCodes.IDR) val idr: String,
    @SerializedName(CurrencyCodes.ILS) val ils: String,
    @SerializedName(CurrencyCodes.INR) val inr: String,
    @SerializedName(CurrencyCodes.ISK) val isk: String,
    @SerializedName(CurrencyCodes.JPY) val jpy: String,
    @SerializedName(CurrencyCodes.KRW) val krw: String,
    @SerializedName(CurrencyCodes.MXN) val mxn: String,
    @SerializedName(CurrencyCodes.MYR) val myr: String,
    @SerializedName(CurrencyCodes.NOK) val nok: String,
    @SerializedName(CurrencyCodes.NZD) val nzd: String,
    @SerializedName(CurrencyCodes.PHP) val php: String,
    @SerializedName(CurrencyCodes.PLN) val pln: String,
    @SerializedName(CurrencyCodes.RON) val ron: String,
    @SerializedName(CurrencyCodes.RUB) val rub: String,
    @SerializedName(CurrencyCodes.SEK) val sek: String,
    @SerializedName(CurrencyCodes.SGD) val sgd: String,
    @SerializedName(CurrencyCodes.THB) val thb: String,
    @SerializedName(CurrencyCodes.TRY) val lira: String,
    @SerializedName(CurrencyCodes.USD) val usd: String,
    @SerializedName(CurrencyCodes.ZAR) val zar: String
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