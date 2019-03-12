package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class ErrorPoint (
    @SerializedName("message")
    val message : String,

    @SerializedName("code")
    val code : Int
)