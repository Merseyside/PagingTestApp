package com.merseyside.pagingtestapp.presentation.view.fragment.detailfragment.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.merseyside.pagingtestapp.BR
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.databinding.FragmentDetailBinding
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.presentation.base.BasePropertyFragment
import com.merseyside.pagingtestapp.presentation.di.component.DaggerDetailFragmentComponent
import com.merseyside.pagingtestapp.presentation.di.module.DetailFragmentModule
import com.merseyside.pagingtestapp.presentation.view.fragment.detailfragment.model.DetailViewModel
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.adapter.PhotoAdapter
import com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model.SharedViewModel

class DetailFragment : BasePropertyFragment<FragmentDetailBinding, DetailViewModel>() {

    private val TAG = javaClass.simpleName

    private lateinit var sharedViewModel : SharedViewModel

    private lateinit var adapter : PhotoAdapter

    companion object {
        fun newInstance() : DetailFragment {
            return DetailFragment()
        }
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun performInjection() {
        DaggerDetailFragmentComponent.builder()
                .appComponent(getAppComponent())
                .detailFragmentModule(getDetailFragmentModule())
                .build().inject(this)
    }

    private fun getDetailFragmentModule() : DetailFragmentModule {
        return DetailFragmentModule(this)
    }

    override fun loadingObserver(isLoading: Boolean) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }


    private fun init() {

        sharedViewModel.getPropertyContainerLiveData().observe(this, Observer<PropertyModel> {
            adapter = PhotoAdapter(getApplicationContext(), it.photoUrls?.toTypedArray())

            viewDataBinding.property.gallery.adapter = adapter
            viewDataBinding.property.areas.text = it.getAreas()
            viewDataBinding.property.floor.text = it.getFloors()
            viewDataBinding.property.roomsAndAddress.text = it.getRoomsWithAddress()
        })
    }


}