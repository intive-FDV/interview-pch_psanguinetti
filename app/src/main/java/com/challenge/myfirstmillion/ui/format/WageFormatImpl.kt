package com.challenge.myfirstmillion.ui.format

import java.text.DecimalFormat
import java.text.NumberFormat
import javax.inject.Inject

/**
 *this could be improved by getting the real days worked count of each year, calculating taxes, and taking into account time off
 */
class WageFormatImpl @Inject constructor(
    val hoursPerDay: Int,
    val daysPerWeek: Int,
    val weeksInYear: Int
) :
    WageFormat {
    //Format example: $1,234,567 USD
    override fun getYearlylWage(hourlyWage: Int): String {
        //8 hours per day assumed
        val wagePerDay = hourlyWage * hoursPerDay

        //5 days per week
        val wagePerWeek = wagePerDay * daysPerWeek

        //52 weeks per year. Average
        val wagePerYear = wagePerWeek * weeksInYear

        val formatter: NumberFormat = DecimalFormat("#,###")
        // TODO CurrencyFormat could be helpfull.
        // val formatter: NumberFormat = NumberFormat.getCurrencyInstance()

        //TODO move the template to resources
        return "${formatter.format(wagePerYear)} USD"
    }
}