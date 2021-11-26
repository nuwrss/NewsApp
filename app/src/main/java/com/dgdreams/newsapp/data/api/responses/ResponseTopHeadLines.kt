package com.dgdreams.newsapp.data.api.responses

import com.dgdreams.newsapp.data.model.News
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseTopHeadLines(

    @SerializedName("status")
val   status_code : String,
    @Expose
@SerializedName("results")
val news: List<News>
)