package com.merseyside.pagingtestapp.presentation.navigation

import androidx.fragment.app.Fragment
import com.merseyside.pagingtestapp.presentation.view.fragment.detailfragment.view.DetailFragment
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.view.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MainFragmentScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainFragment.newInstance()
        }
    }

    class DetailFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return DetailFragment.newInstance()
        }
    }
}