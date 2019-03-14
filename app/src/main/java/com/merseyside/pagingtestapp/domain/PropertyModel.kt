package com.merseyside.pagingtestapp.domain

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil

data class PropertyModel (

        val id : Long,
        val photoCount : Int,
        val photoUrls : List<Photos>?,
        val price : Int,
        val roomCount : Int,
        val address : String,
        val totalArea : Int,
        val livingArea : Int,
        val kitchenArea : Int,
        val floorsCount : Int,
        val floor : Int) {

    private val TAG = javaClass.simpleName

    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<PropertyModel> = object : DiffUtil.ItemCallback<PropertyModel>() {
            override fun areItemsTheSame(@NonNull oldItem: PropertyModel, @NonNull newItem: PropertyModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(@NonNull oldItem: PropertyModel, @NonNull newItem: PropertyModel): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    fun getCover() : String? {
        return if (!photoUrls.isNullOrEmpty()) {
            photoUrls.first().sizeMediumUrl
        }
        else {
            null
        }
    }

    fun getRoomsWithAddress() : String {
        return "$roomCount-к, $address"
    }

    fun getAreas() : String {
        return "$totalArea / $livingArea / $kitchenArea m2"
    }

    fun getFloors() : String {
        return "\u2022 Этаж $floor из $floorsCount"
    }

    fun getFormattedPrice() : String {
        var stringPrice = price.toString()

        var result = ""

        while(true) {

            val substring = stringPrice.takeLast(3)
            stringPrice = stringPrice.removeSuffix(substring)

            result = if (result.isNotEmpty()) {
                "$substring $result"
            } else {
                substring
            }

            if (substring.length != 3) {
                break
            }
        }

        return "$result \u20BD"
    }
}