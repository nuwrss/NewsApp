package com.dgdreams.newsapp.di.component

import com.dgdreams.newsapp.di.FragmentScope
import com.dgdreams.newsapp.di.module.FragmentModule
import com.dgdreams.newsapp.ui.CountriesFragment
import com.dgdreams.newsapp.ui.NewsFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: NewsFragment)
    fun inject(fragment: CountriesFragment)
}