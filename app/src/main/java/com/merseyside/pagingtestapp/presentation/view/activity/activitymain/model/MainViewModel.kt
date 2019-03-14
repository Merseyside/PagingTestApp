package com.merseyside.pagingtestapp.presentation.view.activity.activitymain.model

import com.merseyside.pagingtestapp.presentation.base.BasePropertyViewModel
import com.merseyside.pagingtestapp.presentation.navigation.Screens
import ru.terrakok.cicerone.Router

class MainViewModel(private val router : Router) : BasePropertyViewModel() {
    override fun dispose() {
    }

    override fun clearDisposables() {
    }

    fun navigateToMainFragment() {
        router.newRootChain(Screens.MainFragmentScreen())
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return false
    }
}