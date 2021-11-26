package com.dgdreams.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgdreams.newsapp.data.model.News
import com.dgdreams.newsapp.data.repo.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) :ViewModel() {
    val countryName: MutableLiveData<String> = MutableLiveData()
    init {
        onCountryNameChanged("il")
        fetchNews()
    }


    fun onCountryNameChanged(name: String) {

        countryName.value = name
    }


      fun fetchNews( ){
        viewModelScope.launch {

           newsRepository.fetchTopHeadLines(countryName.value)

        }
    }

    fun getNews(): MutableLiveData<List<News>> {
        return newsRepository.data
    }
}

