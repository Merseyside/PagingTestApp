package com.merseyside.pagingtestapp.domain.interactor

import com.merseyside.pagingtestapp.domain.Property
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.domain.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetPropertiesInteractor @Inject constructor(threadExecutor: ThreadExecutor,
                                                  postExecutionThread: PostExecutionThread,
                                                  private val dataRepository: DataRepository)
    : SingleUseCase<List<Property>, GetPropertiesInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Params?): Single<List<Property>> {
        return dataRepository.getProperties(params!!.limit, params.offset)
    }


    data class Params(val limit : Int, val offset : Int)
}