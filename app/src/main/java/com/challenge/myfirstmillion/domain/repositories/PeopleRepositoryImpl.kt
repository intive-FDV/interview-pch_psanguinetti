package com.challenge.myfirstmillion.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.myfirstmillion.data.local.AppDatabase
import com.challenge.myfirstmillion.data.local.model.UserDB
import com.challenge.myfirstmillion.domain.mediator.MergeDataSource
import com.challenge.myfirstmillion.domain.mediator.PeoplePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val mergeDataSource: MergeDataSource
) : PeopleRepository {

    companion object {
        //TODO this parameters should be injected by build pattern. Remote config candidate!
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = 20
        private const val PREFETCH = 10
    }

    override fun getPostSteam(): Flow<PagingData<UserDB>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = INITIAL_LOAD_SIZE,
                prefetchDistance = PREFETCH,
                enablePlaceholders = false
            )
        ) {
            PeoplePagingSource(
                mergeDataSource
            )
        }.flow
    }

    override fun update(userId: String, hourlyWage: Int) {
        db.peopleDao().update(userId, hourlyWage)
    }
}