package com.merseyside.propertyapi.entity.response

import com.google.gson.annotations.SerializedName
import com.merseyside.propertyapi.entity.response.point.ErrorPoint

abstract class BaseResponse {

    @SerializedName("errors")
    val error: ErrorPoint? = null
}