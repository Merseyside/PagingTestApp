package com.merseyside.pagingtestapp.presentation.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.merseyside.pagingtestapp.domain.interactor.GetPropertiesInteractor
import com.merseyside.pagingtestapp.domain.repository.datasource.PropertyDataSourceFactory
import com.merseyside.pagingtestapp.presentation.view.activity.model.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity : AppCompatActivity) {

    @Provides
    internal fun mainViewModelProvider(propertyDataSourceFactory: PropertyDataSourceFactory): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(propertyDataSourceFactory)
    }

    @Provides
    internal fun provideMainViewModel(factory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

    class MainViewModelProviderFactory(
            private val propertyDataSourceFactory: PropertyDataSourceFactory)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(propertyDataSourceFactory) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}