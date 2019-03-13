package com.merseyside.pagingtestapp.domain

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil

data class PropertyModel(

        val id : Long,
        val photoCount : Int,
        val photoUrls : List<Photos>?,
        val price : Int,
        val roomCount : Int,
        val addresses : String,
        val totalArea : Int,
        val livingArea : Int,
        val kitchenArea : Int,
        val floorsCount : Int,
        val floor : Int) {

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

}