package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.domain.mediator.MergeDataSource
import com.challenge.myfirstmillion.domain.mediator.MergeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MergeSourceModule {
    @Binds
    abstract fun bindsMergeSource(
        mergeDataSourceImpl: MergeDataSourceImpl
    ): MergeDataSource
}