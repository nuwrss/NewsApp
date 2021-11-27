package com.dgdreams.newsapp.data.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "news")

data class News (



    @ColumnInfo(name = "published_date")
    @NotNull
    @Expose
    @SerializedName("pubDate")
    val publish_Date: String,
    @PrimaryKey
    @ColumnInfo(name = "title")
    @NotNull
    @Expose
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "link")
    @NotNull
    @Expose
    @SerializedName("link")
    val link: String,
    @ColumnInfo(name = "country")
    var country :String
)


