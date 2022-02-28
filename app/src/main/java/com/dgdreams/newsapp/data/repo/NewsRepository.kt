package com.dgdreams.newsapp.data.repo

import android.util.Log
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.model.News
import com.dgdreams.newsapp.utilis.SharedPrefs
import javax.inject.Inject

class NewsRepository @Inject constructor( private val apiService: ApiService, private val databaseService: DatabaseService,
private val sharedPrefs: SharedPrefs) {



     suspend fun fetchTopHeadLines(country: String?):List<News> {

         try {

             val response =  apiService.getNews(country)
              if (response.status_code.equals("success")){
                  // insert the news to the database
                 for (news in response.news){
                     if (country != null) {
                         news.country=country
                     }
                     insert(news)
                 }
                  sharedPrefs.saveUpdatedDat()
             }
         }catch (e:Exception){
             Log.e("NewsRepo",e.toString())
         }
         return databaseService.newsDao().getByCountryDesc(country!!)
    }


    private suspend fun insert(news: News) {
        databaseService.newsDao().insert(news)
    }
}