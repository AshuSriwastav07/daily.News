package com.example.assignment_a.DataFiles

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiInterface {
    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<newsDataClass>

    @GET("everything")
    fun getFinanceNews(
        @Query("q") query: String,  // Query keyword
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey: String  // Your API key
    ): Call<newsDataClass>

    @GET("everything")
    fun getDetailNews(
        @Query("q") query: String,  // Query keyword
        @Query("apiKey") apiKey: String  // Your API key
    ): Call<newsDataClass>

    @GET("everything")
    fun getUserDefineTabData(
        @Query("q") query: String,  // Query keyword
        @Query("apiKey") apiKey: String  // Your API key
    ): Call<newsDataClass>

    @GET("everything")
    fun getStoryHighlight(
        @Query("q") query: String,  // Query keyword
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey: String  // Your API key
    ):Call<newsDataClass>
    data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Article>)

}
