package com.app.commondata.api

import com.app.commondata.dto.StocksListResponseDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Used to connect to the Unsplash API to fetch photos
 */
interface ApiService {

    @GET("/stocks")
    suspend fun fetchStocks(
        @Query("apikey") apikey: String,
        @Query("outputsize") recordCount: Int = 50,
    ): Response<StocksListResponseDTO>

    companion object {
        const val BASE_URL = "https://api.twelvedata.com"
        const val API_KEY= "b4504eae3b444104a6504719dd8531bc"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
