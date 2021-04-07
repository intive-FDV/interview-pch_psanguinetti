package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.domain.repositories.PeopleRepository
import com.challenge.myfirstmillion.domain.repositories.PeopleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository
}