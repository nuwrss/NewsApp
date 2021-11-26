package com.dgdreams.newsapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
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

class CountriesFragment: BaseFragment()  {
    @Inject
    lateinit var viewModel: MainViewModel
    companion object {

        const val TAG = "CountriesFragment"

        fun newInstance(): CountriesFragment {
            val args = Bundle()
            val fragment = CountriesFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun provideLayoutId(): Int =
        R.layout.contrieslayout


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    lateinit var usButton: Button
    lateinit var israelButton:Button
    override fun setupView(view: View) {

        usButton=view.findViewById(R.id.usButton)
        israelButton=view.findViewById(R.id.israelButton)

        usButton.setOnClickListener(View.OnClickListener {
            viewModel.onCountryNameChanged("us")

        })

        israelButton.setOnClickListener(View.OnClickListener {
            viewModel.onCountryNameChanged("il")

        })


    }

    override fun setupObservers() {

    }
}