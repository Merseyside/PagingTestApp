package com.merseyside.pagingtestapp.presentation.view.activity.activitymain.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.merseyside.pagingtestapp.BR
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.databinding.ActivityMainBinding
import com.merseyside.pagingtestapp.presentation.base.BasePropertyActivity
import com.merseyside.pagingtestapp.presentation.di.component.DaggerMainActivityComponent
import com.merseyside.pagingtestapp.presentation.di.module.MainActivityModule
import com.merseyside.pagingtestapp.presentation.view.activity.activitymain.model.MainViewModel
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model.SharedViewModel
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : BasePropertyActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var sharedViewModel: SharedViewModel

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var navigator : Navigator = object : SupportAppNavigator(this, R.id.container) {

        override fun applyCommand(command: Command?) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }

        override fun setupFragmentTransaction(command: Command?,
                                              currentFragment: Fragment?,
                                              nextFragment: Fragment?,
                                              fragmentTransaction: FragmentTransaction?) {
            super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun performInjection() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(getMainActivityModule())
                .build().inject(this)
    }

    private fun getMainActivityModule() : MainActivityModule {
        return MainActivityModule(this)
    }

    override fun loadingObserver(isLoading: Boolean) {
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        if (savedInstance == null) {
            viewModel.navigateToMainFragment()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}