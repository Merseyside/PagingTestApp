package com.merseyside.pagingtestapp.presentation.di.component

import android.app.Application
import android.content.Context
import com.merseyside.pagingtestapp.PropertyApplication
import com.merseyside.pagingtestapp.data.repository.PropertyDataStore
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import com.merseyside.pagingtestapp.presentation.di.module.AppModule
import com.merseyside.pagingtestapp.presentation.di.module.NavigationModule
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class])
interface AppComponent {

    fun inject(application : PropertyApplication)

    @ApplicationContext
    fun context() : Context

    fun application() : Application

    fun dataRepository() : DataRepository

    fun threadExecutor() : ThreadExecutor

    fun postExecutionThread() : PostExecutionThread

    fun navigation() : NavigatorHolder

    fun router() : Router
}