package com.merseyside.pagingtestapp.presentation.di.module

import android.app.Application
import android.content.Context
import com.merseyside.pagingtestapp.data.executor.JobExecutor
import com.merseyside.pagingtestapp.data.repository.PropertyDataStore
import com.merseyside.pagingtestapp.data.repository.CloudDataStore
import com.merseyside.pagingtestapp.data.repository.DataRepositoryImpl
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import com.merseyside.pagingtestapp.presentation.UIThread
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application : Application) {

    @Provides
    @ApplicationContext
    fun provideContext() : Context {
        return application
    }

    @Provides
    fun provideApplication() : Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideDataRepository(dataRepository: DataRepositoryImpl) : DataRepository {
        return dataRepository
    }
}