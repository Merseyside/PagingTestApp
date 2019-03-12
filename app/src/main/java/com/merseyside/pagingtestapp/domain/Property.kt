package com.merseyside.pagingtestapp.domain

data class Property(

        val photoCount : Int,
        val photoUrls : List<Photos>,
        val price : Int,
        val roomCount : Int,
        val addresses : String,
        val totalArea : Int,
        val livingArea : Int,
        val kitchenArea : Int,
        val floorsCount : Int,
        val floor : Int
) : Comparable<Property> {

    override fun compareTo(other: Property): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}