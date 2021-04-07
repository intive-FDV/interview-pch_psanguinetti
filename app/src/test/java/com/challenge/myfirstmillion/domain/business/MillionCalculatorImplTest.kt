package com.challenge.myfirstmillion.domain.business

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MillionCalculatorImplTest {

    private lateinit var calculator: MillionCalculator
    private lateinit var errorMsg: String

    @Before
    fun setUp() {
        val hoursPerDay = 8
        val daysPerWeek = 5
        val weeksInYear = 52
        val keepingPercentage = 0.2

        calculator = MillionCalculatorImpl(hoursPerDay, daysPerWeek, weeksInYear, keepingPercentage)
        errorMsg = "The number of months isn't the expected value"
    }

    @Test
    fun one_million_one_year() {
        //GIVEN
        val hourlyWage = 2500

        //WHEN
        val months = calculator.calculateMonths(hourlyWage)

        //THEN
        assertEquals(errorMsg, 12, months)
    }

    @Test
    fun one_million_6_months_ok() {
        //GIVEN
        val hourlyWage = 4808

        //WHEN
        val months = calculator.calculateMonths(hourlyWage)

        //THEN
        assertEquals(errorMsg, 6, months)
    }

    @Test
    fun one_million_more_than_months() {
        //GIVEN
        val hourlyWage = 961

        //WHEN
        val months = calculator.calculateMonths(hourlyWage)

        //THEN

        assertTrue(months > 6)
    }

    @Test
    fun one_million_2_years_3_months_ok() {
        //GIVEN
        val hourlyWage = 1070

        //WHEN
        val months = calculator.calculateMonths(hourlyWage)

        //THEN
        assertEquals(27, months)
    }

    @Test
    fun one_million_2_years_3_months_fails() {
        //GIVEN
        val hourlyWage = 213

        //WHEN
        val months = calculator.calculateMonths(hourlyWage)

        //THEN
        assertNotEquals(27, months)
    }

}