package com.dgdreams.newsapp

import android.app.Application
import androidx.work.*
import com.dgdreams.newsapp.di.component.ApplicationComponent
import com.dgdreams.newsapp.di.component.DaggerApplicationComponent
import com.dgdreams.newsapp.di.module.ApplicationModule
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()



    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}