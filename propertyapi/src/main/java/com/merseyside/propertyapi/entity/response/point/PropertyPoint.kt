package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class PropertyPoint(

        @SerializedName("_id")
        val id : Long,

        @SerializedName("photos_count")
        val photoCount : Int,

        @SerializedName("photos")
        val photos : List<PhotoPoint>,

        @SerializedName("params")
        val params : ParamsPoint
)