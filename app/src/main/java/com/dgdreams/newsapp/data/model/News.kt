package com.dgdreams.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "news")

data class News (
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long,
    @ColumnInfo(name = "published_date")
    @NotNull
    @Expose
    @SerializedName("published_date")
    val publish_Date: String,
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
)


