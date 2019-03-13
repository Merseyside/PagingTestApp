package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class ParamsPoint(
        @SerializedName("price")
        val price : Int,

        @SerializedName("rooms_count")
        val roomCount : Int,

        @SerializedName("house_addresses")
        val addresses : List<AddressPoint>?,

        @SerializedName("total_area")
        val totalArea : Int,

        @SerializedName("living_area")
        val livingArea : Int,

        @SerializedName("kitchen_area")
        val kitchenArea : Int,

        @SerializedName("floors_count")
        val floorsCount : Int,

        @SerializedName("floor")
        val floor : Int
)