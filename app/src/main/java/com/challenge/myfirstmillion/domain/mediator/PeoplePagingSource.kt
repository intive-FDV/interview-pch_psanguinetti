package com.challenge.myfirstmillion.domain.mediator

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.challenge.myfirstmillion.data.local.model.UserDB
import javax.inject.Inject

//TODO try https://developer.android.com/topic/libraries/architecture/paging/v3-network-db even if it is on ExperimentalPagingApi
class PeoplePagingSource @Inject constructor(
    private val mergeDataSource: MergeDataSource
) :
    PagingSource<Int, UserDB>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDB> {
        try {
            val page = params.key ?: 1

            val people = mergeDataSource.getPeople(page, params.loadSize)

            var nextPageNumber: Int? = null
            if (!people.isEmpty()) {
                nextPageNumber = page + 1
            }

            return LoadResult.Page(
                data = people,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            Log.e("PagingSource: ", e.message!!)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserDB>): Int {
        return 1
    }

}