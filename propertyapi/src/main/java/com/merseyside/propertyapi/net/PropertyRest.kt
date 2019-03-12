package com.merseyside.propertyapi.net

import com.merseyside.propertyapi.entity.response.PropertyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyRest {

    @GET("api/v1/offers/?filter[region_id]=1054&query[0][deal_type]=sell&query[0][rubric]=flats&query[0][status]=published&sort=-creation_date")
    fun getProperties(@Query("limit") limit : Int, @Query("offset") offset : Int) : Single<PropertyResponse>
}