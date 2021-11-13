package com.dgdreams.newsapp.data.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dgdreams.newsapp.data.api.ApiService
import com.dgdreams.newsapp.data.local.db.DatabaseService
import com.dgdreams.newsapp.data.repo.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadNewsBackGround(context: Context, workerParams: WorkerParameters,private val repository: NewsRepository ) : CoroutineWorker(context, workerParams)
{



    override suspend fun doWork(): Result {

            Log.e("Worlk manager ", "work")
        repository.fetchTopHeadLines()
            return Result.success()

    }

}