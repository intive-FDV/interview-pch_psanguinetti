package com.challenge.myfirstmillion.domain.mediator

import com.challenge.myfirstmillion.data.local.model.UserDB

interface MergeDataSource {
    suspend fun getPeople(page: Int, loadSize: Int): List<UserDB>
}