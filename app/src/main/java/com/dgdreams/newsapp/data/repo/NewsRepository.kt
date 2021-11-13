package com.dgdreams.newsapp.data.repo

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.api.responses.ResponseTopHeadLines
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.model.News
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService,private val databaseService: DatabaseService) {

    lateinit var data :List<News>
     suspend fun fetchTopHeadLines(){
         try {
             val response =  apiService.getNews()
              if (response.status_code == 200){
                 databaseService.newsDao().deleteAll()
                 for (news in response.news){

                     insert(news)
                 }

             }
         }catch (e:Exception){
             Log.e("NewsRepo",e.toString())
         }finally {
             data = databaseService.newsDao().getAll()
         }



    }





    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(news: News) {
        databaseService.newsDao().insert(news)
    }
}