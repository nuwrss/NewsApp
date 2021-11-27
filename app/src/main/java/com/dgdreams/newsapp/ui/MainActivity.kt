package com.dgdreams.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.R
import com.dgdreams.newsapp.adapters.NewsAdapter
import com.dgdreams.newsapp.data.model.News
import com.dgdreams.newsapp.di.component.ActivityComponent
import com.dgdreams.newsapp.di.component.DaggerActivityComponent
import com.dgdreams.newsapp.di.module.ActivityModule
import com.dgdreams.newsapp.utilis.SharedPrefs
import com.dgdreams.newsapp.workmanager.DownloadByWorkManager
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