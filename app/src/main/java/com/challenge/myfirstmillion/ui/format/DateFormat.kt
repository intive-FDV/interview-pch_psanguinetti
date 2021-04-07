package com.challenge.myfirstmillion.ui.format

interface DateFormat {
    //Format example: 2 years, 9 months
    fun getYearsAndMonths(months: Int): String
}