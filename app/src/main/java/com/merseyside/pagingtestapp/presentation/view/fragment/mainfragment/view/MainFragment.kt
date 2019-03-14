package com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.merseyside.pagingtestapp.BR
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.databinding.FragmentMainBinding
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.data.repository.datasource.State
import com.merseyside.pagingtestapp.presentation.base.BasePropertyFragment
import com.merseyside.pagingtestapp.presentation.di.component.DaggerMainFragmentComponent
import com.merseyside.pagingtestapp.presentation.di.module.MainFragmentModule
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.adapter.PropertyAdapter
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model.MainFragmentViewModel
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model.SharedViewModel
import com.merseyside.pagingtestapp.utils.WrapContentLinearLayoutManager
import com.upstream.basemvvmimpl.presentation.adapter.BaseAdapter

class MainFragment : BasePropertyFragment<FragmentMainBinding, MainFragmentViewModel>() {

    private val TAG = javaClass.simpleName

    private lateinit var adapter : PropertyAdapter

    private lateinit var sharedViewModel : SharedViewModel

    private val propertyObserver = Observer<PagedList<PropertyModel>> {
        adapter.submitList(it)
    }

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerMainFragmentComponent.builder()
                .appComponent(getAppComponent())
                .mainFragmentModule(getMainFragmentModule())
                .build().inject(this)
    }

    private fun getMainFragmentModule() : MainFragmentModule {
        return MainFragmentModule(this)
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doLayout()
        init()
    }

    private fun doLayout() {

        viewDataBinding.propertyList.layoutManager = WrapContentLinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false)
        adapter = PropertyAdapter()
        adapter.addOnItemClickListener(object : BaseAdapter.AdapterClickListener {
            override fun onItemClicked(obj: Any) {
                sharedViewModel.shareProperty(obj as PropertyModel)
                viewModel.navigateToDetailsFragment()
            }
        })

        viewDataBinding.propertyList.adapter = adapter
    }

    private fun init() {
        viewModel.loadProperties()
        viewModel.propertyLiveData.observe(this, propertyObserver)

        viewModel.getStateLiveData().observe(this, Observer<State> {
            when(it) {
                State.ERROR ->
                    showErrorMsg(it.getMessage(), "Retry") {
                        viewModel.retry()
                    }

                else -> {}
            }
        })

    }
}
