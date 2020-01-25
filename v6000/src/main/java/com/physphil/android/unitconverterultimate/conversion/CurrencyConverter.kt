package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.models.Currency
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity
import java.math.BigDecimal
import java.math.RoundingMode

object CurrencyConverter {

    fun convert(
        value: BigDecimal,
        initial: Currency,
        final: Currency,
        rates: List<RateEntity>
    ): BigDecimal {
        val toEuro = rates.first { it.code == initial.code.value }.rate
        val fromEuro = rates.first { it.code == final.code.value }.rate
        // Convert the value to Euro, then convert from Euro to desired unit
        val valueInEuro = value.divide(BigDecimal(toEuro), SCALE_RESULT, RoundingMode.HALF_UP)
        return valueInEuro * BigDecimal(fromEuro)
    }
}