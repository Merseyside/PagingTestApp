package com.merseyside.propertyapi.entity.response.point

import com.google.gson.annotations.SerializedName

data class PhotoPoint(
        @SerializedName("description")
        val description : String,

        @SerializedName("is_cover")
        val isCover : Boolean,

        @SerializedName("is_facade")
        val isfacade : Boolean,

        @SerializedName("position")
        val position : Int,

        @SerializedName("1200x900p")
        val sizeExtraUrl : String,

        @SerializedName("834x624p")
        val sizeBigUrl : String,

        @SerializedName("360x270cp")
        val sizeMediumUrl : String,

        @SerializedName("258x193cp")
        val sizeSmallUrl : String,

        @SerializedName("216x162cp")
        val sizeExtraSmall : String,

        @SerializedName("70x70cp")
        val iconUrl : String
)