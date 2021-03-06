package com.dgdreams.newsapp.di.module

import android.app.Application
import androidx.room.Room
import androidx.work.WorkerFactory
import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.api.RetrofitBuilder
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.repo.NewsRepository
import com.dgdreams.newsapp.utilis.SharedPrefs
import com.dgdreams.newsapp.workmanager.DaggerWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application
    @Provides
    @Singleton
    fun getApiService(): ApiService =
        RetrofitBuilder.getRetrofit("https://newsdata.io/api/1/"
        )

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "news-db"
        ).build()

    @Provides
    @Singleton
    fun provideSharedPRefs():
            SharedPrefs{
        return SharedPrefs(provideApplication())
    }



    @Provides
    @Singleton
    fun workerFactory(newsRepository: NewsRepository ): WorkerFactory {
        return DaggerWorkerFactory(newsRepository )
    }






}