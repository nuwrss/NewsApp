package com.dgdreams.newsapp.data.local.db.dao

import androidx.room.*
import com.dgdreams.newsapp.data.model.News


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getAll(): List<News>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: News)

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}