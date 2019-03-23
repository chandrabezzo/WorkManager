package com.bezzo.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerService(context: Context, parameter: WorkerParameters): Worker(context, parameter) {
    override fun doWork(): Result {
        Log.i("Worker", "Sync")
        return Result.success()
    }
}

class PeriodicService(context: Context, parameter: WorkerParameters): Worker(context, parameter){
    override fun doWork(): Result {
        Log.i("Periodic", "Periode")
        return Result.success()
    }
}