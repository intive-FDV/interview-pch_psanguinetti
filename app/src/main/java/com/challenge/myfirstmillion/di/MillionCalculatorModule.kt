package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.di.utils.Constants
import com.challenge.myfirstmillion.domain.business.MillionCalculator
import com.challenge.myfirstmillion.domain.business.MillionCalculatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MillionCalculatorModule {
    @Provides
    fun provideMillionCalculator(
    ): MillionCalculator {
        return MillionCalculatorImpl(
            Constants.HOURS_PER_DAY,
            Constants.DAYS_PER_WEEK,
            Constants.WEEKS_IN_YEAR,
            Constants.KEEPING_COEFFICIENT
        )
    }
}