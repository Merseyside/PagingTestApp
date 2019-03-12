package com.merseyside.propertyapi.entity.response

import com.google.gson.annotations.SerializedName
import com.merseyside.propertyapi.entity.response.point.PropertyPoint

data class PropertyResponse(
        @SerializedName("result")
        val propertyList : List<PropertyPoint>
) : BaseResponse()