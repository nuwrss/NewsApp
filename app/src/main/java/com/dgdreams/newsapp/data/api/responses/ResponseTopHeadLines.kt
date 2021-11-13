package com.dgdreams.newsapp.data.api.responses

import com.dgdreams.newsapp.data.model.News
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseTopHeadLines(

    @SerializedName("statusCode")
val   status_code : Int,
    @Expose
@SerializedName("articles")
val news: List<News>
)