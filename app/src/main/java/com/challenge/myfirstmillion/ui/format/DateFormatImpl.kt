package com.challenge.myfirstmillion.ui.format

import javax.inject.Inject

class DateFormatImpl @Inject constructor() : DateFormat {
    //Format example: 2 years, 9 months
    override fun getYearsAndMonths(months: Int): String {
        val years = months / 12
        val modMonths = months % 12

        //TODO move the template to resources
        return "${years} years, ${modMonths} months"
    }
}