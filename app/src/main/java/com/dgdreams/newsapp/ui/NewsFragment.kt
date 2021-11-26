package com.dgdreams.newsapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgdreams.newsapp.R
import com.dgdreams.newsapp.adapters.NewsAdapter
import com.dgdreams.newsapp.data.model.News
import com.dgdreams.newsapp.di.component.FragmentComponent
import com.dgdreams.newsapp.ui.base.BaseFragment
import javax.inject.Inject

class NewsFragment: BaseFragment()  {
    @Inject
    lateinit var viewModel: MainViewModel
    companion object {

        const val TAG = "NewsFragment"

        fun newInstance(): NewsFragment {
            val args = Bundle()
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun provideLayoutId(): Int =
        R.layout.newsfragmentlayout


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        var rv = view.findViewById<RecyclerView>(R.id.rv)
         progressBar = view.findViewById<ProgressBar>(R.id.proBar)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        rv.layoutManager = linearLayoutManager
         adapter = NewsAdapter(news)
        rv.adapter = adapter
    }
    lateinit var progressBar : ProgressBar
    lateinit var adapter: NewsAdapter
    var news : MutableList<News> = arrayListOf()
    override fun setupObservers() {
        viewModel.getNews().observe(this, Observer {
            news.clear()
            news.addAll(it)
            adapter.notifyDataSetChanged()
            progressBar.visibility=View.GONE
        })

        viewModel.countryName.observe(this, Observer {
            progressBar.visibility=View.VISIBLE
            viewModel.fetchNews()
        })
    }
}