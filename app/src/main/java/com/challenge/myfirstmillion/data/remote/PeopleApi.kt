package com.challenge.myfirstmillion.data.remote

import com.challenge.myfirstmillion.BuildConfig
import com.challenge.myfirstmillion.data.remote.model.UserList
import retrofit2.Call
import retrofit2.http.GET

interface PeopleApi {
    @GET(BuildConfig.MOCKY_OBJECT_ID)
    //TODO Endpoint should support pagination
    fun getPeople(): Call<UserList>
}