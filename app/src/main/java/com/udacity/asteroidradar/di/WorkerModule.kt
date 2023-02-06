package com.udacity.asteroidradar.di

import androidx.work.ListenableWorker
import com.udacity.asteroidradar.worker.ChildWorkerFactory
import com.udacity.asteroidradar.worker.RefreshAsteroidsWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(RefreshAsteroidsWorker::class)
    fun bindHelloWorldWorker(factory: RefreshAsteroidsWorker.Factory): ChildWorkerFactory
}
