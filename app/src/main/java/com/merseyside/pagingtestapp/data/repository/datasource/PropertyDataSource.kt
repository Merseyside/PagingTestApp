package com.merseyside.pagingtestapp.data.repository.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class PropertyDataSource(
        private val dataRepository: DataRepository,
        private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<PropertyModel>() {

    private val TAG = javaClass.simpleName

    var state: MutableLiveData<State> = MutableLiveData()

    private var retryCompletable: Completable? = null

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PropertyModel>) {
        updateState(State.LOADING, "")

        dataRepository.getProperties(params.loadSize, params.startPosition)
                .doOnSubscribe {
                    compositeDisposable.add(it)
                }
                .subscribe({ result ->
                    callback.onResult(result)
                }, { throwable ->
                    updateState(State.ERROR, throwable.message ?: "Unknown error")
                    setRetry(Action { loadRange(params, callback) })
                })
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PropertyModel>) {
        updateState(State.LOADING, "")

        dataRepository.getProperties(params.requestedLoadSize, params.requestedStartPosition)
                .doOnSubscribe {
                    Log.d(TAG, "subscribed")
                    compositeDisposable.add(it)
                }//.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->

                    callback.onResult(result, 0)

                }, { throwable ->
                    updateState(State.ERROR, throwable.message ?: "Unknown error")
                    setRetry(Action { loadInitial(params, callback) })
                })
    }

    private fun updateState(state: State, message : String) {
        state.setMessage(message)
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}