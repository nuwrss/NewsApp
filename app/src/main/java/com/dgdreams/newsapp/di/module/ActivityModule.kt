package com.dgdreams.newsapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkerFactory
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.repo.NewsRepository
import com.dgdreams.newsapp.ui.MainActivity
import com.dgdreams.newsapp.ui.MainViewModel
import com.dgdreams.newsapp.utilis.ViewModelProviderFactory
import com.dgdreams.newsapp.workmanager.DaggerWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: MainActivity) {



    @Provides
    fun provideMainViewModel(
        newsRepository: NewsRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(newsRepository)
        })[MainViewModel::class.java]
}