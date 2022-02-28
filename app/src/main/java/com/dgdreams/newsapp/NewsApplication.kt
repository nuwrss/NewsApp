package com.dgdreams.newsapp

import android.app.Application
import androidx.work.*
import com.dgdreams.newsapp.di.component.ApplicationComponent
import com.dgdreams.newsapp.di.module.ApplicationModule
import com.dgdreams.newsapp.workmanager.DownloadByWorkManager
import javax.inject.Inject
import java.util.concurrent.TimeUnit
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutionException

class NewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    private val workManagerTag="downloadNews";

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        configureWorkManager();
        if (!isWorkScheduled(workManagerTag)){
            scheduleWork(workManagerTag);
        }
    }
    private fun isWorkScheduled(tag: String): Boolean {
        val instance = WorkManager.getInstance(this)
        val statuses: ListenableFuture<List<WorkInfo>> = instance.getWorkInfosByTag(tag)
        return try {
            var running = false
            val workInfoList: List<WorkInfo> = statuses.get()
            for (workInfo in workInfoList) {
                val state = workInfo.state
                if (state==WorkInfo.State.RUNNING || state== WorkInfo.State.ENQUEUED){
                    running= true
                }
            }
            running
        } catch (e: ExecutionException) {
            e.printStackTrace()
            false
        } catch (e: InterruptedException) {
            e.printStackTrace()
            false
        }
    }

    fun scheduleWork(tag: String?) {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val downloadWork = PeriodicWorkRequest.Builder(
            DownloadByWorkManager::class.java, 15,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(tag!!, ExistingPeriodicWorkPolicy.KEEP , downloadWork);

    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    @Inject
    lateinit var workerFactory: WorkerFactory

    private fun configureWorkManager() {
        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

        WorkManager.initialize(this, config)
    }
}