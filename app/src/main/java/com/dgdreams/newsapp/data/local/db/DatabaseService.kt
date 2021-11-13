package com.dgdreams.newsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgdreams.newsapp.data.local.db.dao.NewsDao
import com.dgdreams.newsapp.data.model.News
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        News::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}