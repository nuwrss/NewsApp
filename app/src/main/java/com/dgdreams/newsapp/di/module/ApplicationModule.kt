package com.dgdreams.newsapp.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import androidx.work.*
import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.api.RetrofitBuilder
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.repo.NewsRepository
import com.dgdreams.newsapp.data.workmanager.LoadNewsBackGround
import com.dgdreams.newsapp.ui.MainViewModel
import com.dgdreams.newsapp.utilis.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application
    @Provides
    @Singleton
    fun getApiService(): ApiService =
        RetrofitBuilder.getRetrofit("https://google-news1.p.rapidapi.com"
        )

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "news-db"
        ).build()






}