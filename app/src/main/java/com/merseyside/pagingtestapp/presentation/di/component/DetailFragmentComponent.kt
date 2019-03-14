package com.merseyside.pagingtestapp.presentation.di.component

import com.merseyside.pagingtestapp.presentation.di.module.DetailFragmentModule
import com.merseyside.pagingtestapp.presentation.view.fragment.detailfragment.view.DetailFragment
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [DetailFragmentModule::class])
interface DetailFragmentComponent {

    fun inject(fragment : DetailFragment)
}