package com.challenge.myfirstmillion.ui.format

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DateFormatImplTest {

    private lateinit var formatter: DateFormat

    @Before
    fun setup() {
        formatter = DateFormatImpl()
    }

    @Test
    fun one_years_1_months_format() {
        //GIVEN
        val months = 13

        //WHEN
        val timePenting = formatter.getYearsAndMonths(months)

        //THEN
        //Format example: 2 years, 9 months
        assertEquals("Error on date format (X years, Y months)", "1 years, 1 months", timePenting)
    }

    @Test
    fun zero_years_11_months_format() {
        //GIVEN
        val months = 11

        //WHEN
        val timePenting = formatter.getYearsAndMonths(months)

        //THEN
        //Format example: 2 years, 9 months
        assertEquals("Error on date format (X years, Y months)", "0 years, 11 months", timePenting)
    }

    @Test
    fun one_years_11_months_format() {
        //GIVEN
        val months = 23

        //WHEN
        val timePenting = formatter.getYearsAndMonths(months)

        //THEN
        //Format example: 2 years, 9 months
        assertEquals("Error on date format (X years, Y months)", "1 years, 11 months", timePenting)
    }

    @Test
    fun four_years_0_months_format() {
        //GIVEN
        val months = 48

        //WHEN
        val timePenting = formatter.getYearsAndMonths(months)

        //THEN
        //Format example: 2 years, 9 months
        assertEquals("Error on date format (X years, Y months)", "4 years, 0 months", timePenting)
    }
}