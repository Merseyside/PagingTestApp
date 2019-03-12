package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class AddressPoint(
        @SerializedName("street")
        val streetPoint : StreetPoint,

        @SerializedName("house_number")
        val houseNumber : String
)