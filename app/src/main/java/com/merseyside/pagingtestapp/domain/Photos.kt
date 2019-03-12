package com.merseyside.pagingtestapp.domain

data class Photos(
        val description : String,
        val isCover : Boolean,
        val isFacade : Boolean,
        val position : Int,
        val sizeExtraUrl : String,
        val sizeBigUrl : String,
        val sizeMediumUrl : String,
        val sizeSmallUrl : String,
        val sizeExtraSmall : String,
        val iconUrl : String
)