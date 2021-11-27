package com.dgdreams.newsapp.di.component

import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.repo.NewsRepository
import com.dgdreams.newsapp.di.module.ApplicationModule
import com.dgdreams.newsapp.utilis.SharedPrefs
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: NewsApplication)
    fun getApiService(): ApiService
    fun getDatabaseService(): DatabaseService
    fun getSharedPrefs():SharedPrefs
}