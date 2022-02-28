package com.dgdreams.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.R
import com.dgdreams.newsapp.di.component.ActivityComponent
import com.dgdreams.newsapp.di.module.ActivityModule
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        showCountriesFragment()
        showNewsFragment()
    }

    private fun showNewsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, NewsFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun showCountriesFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.sidFrame, CountriesFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

     private fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}