package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class StreetPoint(
        @SerializedName("name_ru")
        val name : String
)