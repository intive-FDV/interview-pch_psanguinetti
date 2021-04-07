package com.challenge.myfirstmillion.domain.mediator

import com.challenge.myfirstmillion.data.local.AppDatabase
import com.challenge.myfirstmillion.data.local.model.UserDB
import com.challenge.myfirstmillion.data.local.model.toUserDB
import com.challenge.myfirstmillion.data.remote.PeopleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import java.lang.Exception
import javax.inject.Inject

//TODO try https://developer.android.com/topic/libraries/architecture/paging/v3-network-db even if it is on ExperimentalPagingApi
class MergeDataSourceImpl @Inject constructor(
    private val db: AppDatabase,
    private val peopleApi: PeopleApi
) : MergeDataSource {
    override suspend fun getPeople(page: Int, loadSize: Int): List<UserDB> {
        return withContext(Dispatchers.IO) {

            try {
                val response = peopleApi.getPeople().await()
                db.peopleDao().insertAll(response.userList.map { it.toUserDB() })
            }catch (e: Exception){
                //Show data if database is populated
                e.printStackTrace()
                //TODO log in crashlytics
            }

            val offset = (page - 1) * loadSize
            val posts = db.peopleDao().getPeople(loadSize, offset)

            return@withContext posts
        }
    }
}