package com.challenge.myfirstmillion.di

import com.challenge.myfirstmillion.BuildConfig
import com.challenge.myfirstmillion.data.remote.PeopleApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiRestModule {

    @Provides
    fun providePeopleApi(): PeopleApi {
        //TODO inject Interceptor if needed
        /*
        val okHttpClientBuilder = OkHttpClient().newBuilder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClientBuilder.addInterceptor(loggingInterceptor)

        val okHttpClient = okHttpClientBuilder.build()

         */

        return Retrofit.Builder()
            //.client(okHttpClient)
            .baseUrl(BuildConfig.API_REST)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PeopleApi::class.java)
    }

}