package com.dgdreams.newsapp.di.component

import com.dgdreams.newsapp.di.ActivityScope
import com.dgdreams.newsapp.di.module.ActivityModule
import com.dgdreams.newsapp.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}