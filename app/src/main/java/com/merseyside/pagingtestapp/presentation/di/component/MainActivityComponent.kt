package com.merseyside.pagingtestapp.presentation.di.component

import com.merseyside.pagingtestapp.presentation.di.module.MainActivityModule
import com.merseyside.pagingtestapp.presentation.view.activity.view.MainActivity
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(activity : MainActivity)
}