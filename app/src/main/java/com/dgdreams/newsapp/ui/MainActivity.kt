package com.dgdreams.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.dgdreams.newsapp.workmanager.DownloadByWorkManager
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        val news : MutableList<News> = arrayListOf()
        setContentView(R.layout.activity_main)
        var rv = findViewById<RecyclerView>(R.id.rv)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        var adapter = NewsAdapter(news)
        rv.adapter = adapter

        viewModel.getNews().observe(this, Observer {

            news.clear()
            news.addAll(it)
            adapter.notifyDataSetChanged()

        })
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