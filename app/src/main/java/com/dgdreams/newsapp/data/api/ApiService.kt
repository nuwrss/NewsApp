package com.dgdreams.newsapp.data.api

import com.dgdreams.newsapp.data.api.responses.ResponseTopHeadLines
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {
    @GET(Endpoints.TOP_HEADLINES)

    suspend fun getNews(@Query("country") firstPostId: String?="US",
                        @Query("lang") lastPostId: String?="en",
                        @Query("limit") limit: String?="50",
                        @Header(Headers.RAPID_API_HOST) host: String=Headers.RAPID_API_HOST_VALUE,
                        @Header(Headers.RAPID_API_KEY) apiKey: String=Headers.RAPID_API_KEY_VALUE
                        ): ResponseTopHeadLines


}