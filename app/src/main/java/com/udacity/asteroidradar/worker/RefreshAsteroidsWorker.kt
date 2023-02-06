package com.udacity.asteroidradar.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.repository.AsteroidRepository
import retrofit2.HttpException
import javax.inject.Inject

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}

class RefreshAsteroidsWorker(
    appContext: Context,
    params: WorkerParameters,
    private val asteroidRepository: AsteroidRepository
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshAsteroidsWorker"
    }

    class Factory @Inject constructor(
        private val asteroidRepository: AsteroidRepository
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return RefreshAsteroidsWorker(
                appContext,
                params,
                asteroidRepository
            )
        }
    }

    override suspend fun doWork(): Result {
        return try {
            Log.i("RefreshAsteroidsWorker", "doWork")
            asteroidRepository.refreshAsteroidList()
            Log.i("RefreshAsteroidsWorker", "success")
            Result.success()

        } catch (e: HttpException) {
            Result.retry()
        }
    }
}