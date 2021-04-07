package com.challenge.myfirstmillion.domain.business

interface MillionCalculator {
    fun calculateMonths(hourlyWage: Int): Int
}