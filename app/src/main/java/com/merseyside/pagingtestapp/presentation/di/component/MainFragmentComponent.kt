package com.merseyside.pagingtestapp.presentation.di.component

import com.merseyside.pagingtestapp.presentation.di.module.MainFragmentModule
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.view.MainFragment
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainFragmentModule::class])
interface MainFragmentComponent {

    fun inject(fragment : MainFragment)
}