package com.dgdreams.newsapp.data.local.db.dao

import androidx.room.*
import com.dgdreams.newsapp.data.model.News


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getAll(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: News)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

    @Query("SELECT * FROM news WHERE country = :country")
    suspend fun getByCountry(country:String): List<News>
}