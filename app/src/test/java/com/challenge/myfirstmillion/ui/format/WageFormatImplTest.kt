package com.challenge.myfirstmillion.ui.format

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WageFormatImplTest {

    private lateinit var formatter: WageFormat

    @Before
    fun setUp() {
        formatter = WageFormatImpl(8,5,52)
    }
    @Test
    fun yearly_wage_in_usd() {

        //GIVEN
        val hourlyWage = 50

        //WHEN
        val yearlyWage = formatter.getYearlylWage(hourlyWage)

        //THEN
        //Format example: $1,234,567 USD
        assertEquals("The yearly wage is not well calculated", "104,000 USD", yearlyWage)
    }
}