package com.dgdreams.newsapp.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.dgdreams.newsapp.data.repo.NewsRepository
import javax.inject.Inject

class DaggerWorkerFactory (private val newsRepository: NewsRepository) : WorkerFactory() {

    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {

        val workerKlass = Class.forName(workerClassName).asSubclass(CoroutineWorker::class.java)
        val constructor = workerKlass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is DownloadByWorkManager -> {
                instance.newsRepository = newsRepository
            }

        }

        return instance
    }
}