package com.dgdreams.newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.dgdreams.newsapp.data.model.News
import com.dgdreams.newsapp.data.repo.NewsRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainViewModel(private val newsRepository: NewsRepository) :ViewModel() {

    private val newsList = MutableLiveData<List<News>>()
    init {
        fetchNews()
    }

    private fun fetchNews() {

        viewModelScope.launch {


            newsRepository.fetchTopHeadLines()
            newsList.postValue(newsRepository.data)



        }
    }

    fun getNews(): LiveData<List<News>> {
        return newsList
    }
}

