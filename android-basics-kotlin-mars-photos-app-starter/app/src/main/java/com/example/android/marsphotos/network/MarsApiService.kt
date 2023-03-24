package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
// Using Moshi library --> moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Using the Retrofit library --> retrofit object
private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    /* Use the @GET annotation to tell Retrofit that this is GET request, and specify an endpoint, for that web service method.
     * In this case the endpoint is called photos. As mentioned in the previous task,
     * you will be using /photos endpoint in this codelab.
     */
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

object MarsApi{
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}