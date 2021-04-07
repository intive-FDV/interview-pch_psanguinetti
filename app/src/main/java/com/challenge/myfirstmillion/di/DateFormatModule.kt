package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.ui.format.DateFormat
import com.challenge.myfirstmillion.ui.format.DateFormatImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DateFormatModule {

    @Provides
    fun provideDateFormat(
    ): DateFormat {
        return DateFormatImpl()
    }
}