package com.merseyside.pagingtestapp.domain.repository.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PropertyDataSource(
        private val dataRepository: DataRepository,
        private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<PropertyModel>() {

    private val TAG = javaClass.simpleName

    var state: MutableLiveData<State> = MutableLiveData()

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PropertyModel>) {
        updateState(State.LOADING)

        dataRepository.getProperties(params.loadSize, params.startPosition)
                .doOnSubscribe {
                    compositeDisposable.add(it)
                }
                .subscribe({ result ->
                    callback.onResult(result)
                }, { throwable ->
                    throwable.printStackTrace()
                    updateState(State.ERROR)
                })
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PropertyModel>) {
        updateState(State.LOADING)

        dataRepository.getProperties(params.requestedLoadSize, params.requestedStartPosition)
                .doOnSubscribe {
                    Log.d(TAG, "subscribed")
                    compositeDisposable.add(it)
                }//.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->

                    callback.onResult(result, 0)

                }, { throwable ->
                    throwable.printStackTrace()
                    updateState(State.ERROR)
                })
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

}