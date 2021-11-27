package com.dgdreams.newsapp.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dgdreams.newsapp.data.repo.NewsRepository
import android.R






class DownloadByWorkManager (
      val context: Context,
    params: WorkerParameters

    ) : CoroutineWorker(context, params) {

    lateinit var newsRepository: NewsRepository
        override suspend fun doWork(): Result {

                newsRepository.fetchTopHeadLines("il")


            return Result.success()
        }


    }