package com.challenge.myfirstmillion.domain.business

import javax.inject.Inject

/**
 * @param keepingPercentage 0 to 1 (e.g.: 0.2 for 20%)
 */
class MillionCalculatorImpl @Inject constructor(
    val hoursPerDay: Int,
    val daysPerWeek: Int,
    val weeksInYear: Int,
    val keepingPercentage: Double
) : MillionCalculator {

    /**
     *
     * The maths depends on the number of weeks in a ye ar set in the constructor
     *
     */
    override fun calculateMonths(hourlyWage: Int): Int {
        val wagePerMonth = getWagePerMonth(hourlyWage)
        val savings = wagePerMonth * keepingPercentage
        var months = (1000000 / savings).toInt()

        //TODO replace this with a round up method
        if (savings * months < 1000000) {
            months++
        }

        return months
    }

    private fun getWagePerMonth(hourlyWage: Int): Double {
        val avgWeeksPerMonth = weeksInYear / 12.0
        val wagePerMonth = hourlyWage * hoursPerDay * daysPerWeek * avgWeeksPerMonth

        return wagePerMonth
    }
}