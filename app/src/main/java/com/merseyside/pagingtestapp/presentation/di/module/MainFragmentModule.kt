package com.merseyside.pagingtestapp.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.merseyside.pagingtestapp.data.repository.datasource.PropertyDataSourceFactory
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model.MainFragmentViewModel
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainFragmentModule(private val fragment : BaseFragment) {

    @Provides
    internal fun mainViewModelProvider(router : Router,
                                       propertyDataSourceFactory: PropertyDataSourceFactory): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(router, propertyDataSourceFactory)
    }

    @Provides
    internal fun provideMainViewModel(factory: ViewModelProvider.Factory): MainFragmentViewModel {
        return ViewModelProviders.of(fragment, factory).get(MainFragmentViewModel::class.java)
    }

    class MainViewModelProviderFactory(
            private val router : Router,
            private val propertyDataSourceFactory: PropertyDataSourceFactory)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainFragmentViewModel::class.java) {
                return MainFragmentViewModel(router, propertyDataSourceFactory) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}