package com.merseyside.propertyapi.net

import android.content.Context
import com.merseyside.propertyapi.entity.response.PropertyResponse
import com.merseyside.propertyapi.exception.HttpException
import io.reactivex.Single

class PropertyApi(context : Context) {

    private val creator = PropertyResponseCreator(context)

    fun getProperties(limit : Int, offset : Int) : Single<PropertyResponse> {
        return creator.getProperties(limit, offset)
                .onErrorResumeNext { throwable ->
                    if (throwable is retrofit2.HttpException) {
                        Single.error(HttpException(message = throwable.message, code = throwable.code()))
                    } else {
                        Single.error(throwable)
                    }
                }
                .map { response ->
                    if (response.error != null) {
                        throw HttpException(response.error.message, response.error.code)
                    } else {
                        response
                    }
                }
    }

}