package com.dgdreams.newsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.dgdreams.newsapp.NewsApplication
import com.dgdreams.newsapp.di.component.DaggerFragmentComponent
import com.dgdreams.newsapp.di.component.FragmentComponent
import com.dgdreams.newsapp.di.module.FragmentModule

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()

    }


    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((context?.applicationContext as NewsApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(provideLayoutId(), container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(view: View)

    protected abstract fun setupObservers()
}