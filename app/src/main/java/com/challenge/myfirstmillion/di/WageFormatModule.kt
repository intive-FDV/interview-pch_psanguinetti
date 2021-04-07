package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.di.utils.Constants.Companion.DAYS_PER_WEEK
import com.challenge.myfirstmillion.di.utils.Constants.Companion.HOURS_PER_DAY
import com.challenge.myfirstmillion.di.utils.Constants.Companion.WEEKS_IN_YEAR
import com.challenge.myfirstmillion.ui.format.WageFormat
import com.challenge.myfirstmillion.ui.format.WageFormatImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class WageFormatModule {

    @Provides
    fun provideWageFormat(
        // Potential dependencies of this type
    ): WageFormat {
        return WageFormatImpl(
            HOURS_PER_DAY,
            DAYS_PER_WEEK,
            WEEKS_IN_YEAR
        )
    }
}