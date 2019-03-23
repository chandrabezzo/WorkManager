package com.bezzo.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var networkWorker : OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraintSyncWorker = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        btn_run.setOnClickListener {
            networkWorker = OneTimeWorkRequest.Builder(WorkerService::class.java)
                .setConstraints(constraintSyncWorker).build()
            WorkManager.getInstance().enqueue(networkWorker)
        }

        btn_periodic.setOnClickListener {
            val refreshWork = PeriodicWorkRequest.Builder(PeriodicService::class.java,
                et_looping.text.toString().toLong(), TimeUnit.MINUTES)
                .setConstraints(constraintSyncWorker)
                .build()

            WorkManager.getInstance().enqueue(refreshWork)
        }
    }
}
