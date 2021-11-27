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

    @Query("DELETE FROM news WHERE country = :country")
    suspend fun deleteByCountry(country: String)

    @Query("SELECT * FROM news WHERE country = :country")
    suspend fun getByCountry(country:String): List<News>

    @Query("SELECT * FROM news WHERE country = :country ORDER BY published_date  DESC")
    suspend fun getByCountryDesc(country:String): List<News>
}