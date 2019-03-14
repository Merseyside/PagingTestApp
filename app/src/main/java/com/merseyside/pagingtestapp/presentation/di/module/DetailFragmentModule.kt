package com.merseyside.pagingtestapp.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.merseyside.pagingtestapp.presentation.view.fragment.detailfragment.model.DetailViewModel
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class DetailFragmentModule(private val fragment : BaseFragment) {

    @Provides
    internal fun detailViewModelProvider(): ViewModelProvider.Factory {
        return DetailViewModelProviderFactory()
    }

    @Provides
    internal fun provideDetailViewModel(factory: ViewModelProvider.Factory): DetailViewModel {
        return ViewModelProviders.of(fragment, factory).get(DetailViewModel::class.java)
    }

    class DetailViewModelProviderFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == DetailViewModel::class.java) {
                return DetailViewModel() as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}