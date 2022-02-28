package com.dgdreams.newsapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgdreams.newsapp.data.repo.NewsRepository
import com.dgdreams.newsapp.ui.MainViewModel
import com.dgdreams.newsapp.ui.base.BaseFragment
import com.dgdreams.newsapp.utilis.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment) {
    @Provides
    fun provideMainSharedViewModel(
        newsRepository: NewsRepository
    ): MainViewModel = ViewModelProviders.of(
        fragment.requireActivity(), ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(newsRepository)
        })[MainViewModel::class.java]
}