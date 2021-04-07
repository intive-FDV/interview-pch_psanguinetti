package com.challenge.myfirstmillion.domain.repositories

import androidx.paging.PagingData
import com.challenge.myfirstmillion.data.local.model.UserDB
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPostSteam(): Flow<PagingData<UserDB>>
    fun update(userId: String, hourlyWage: Int)
}