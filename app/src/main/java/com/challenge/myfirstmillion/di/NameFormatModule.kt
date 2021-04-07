package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.ui.format.DateFormat
import com.challenge.myfirstmillion.ui.format.DateFormatImpl
import com.challenge.myfirstmillion.ui.format.NameFormat
import com.challenge.myfirstmillion.ui.format.NameFormatImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NameFormatModule {

    @Binds
    abstract fun bindsNameFormat(
        nameFormatImpl: NameFormatImpl
    ): NameFormat
}