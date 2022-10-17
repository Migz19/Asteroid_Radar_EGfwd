package com.example.asteroidradar.retrofit

import com.example.asteroidradar.BuildConfig
import com.example.asteroidradar.Constants
import com.example.asteroidradar.model.PictureOfDay
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") start_date: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): String

}
interface PicApi{
    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): PictureOfDay
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit =
                Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

private val picRetrofit=Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()

object Api{
    val apiCalls:ApiCalls by lazy { retrofit.create(ApiCalls::class.java) }
    val picApi : PicApi by lazy { picRetrofit.create(PicApi::class.java) }
}

