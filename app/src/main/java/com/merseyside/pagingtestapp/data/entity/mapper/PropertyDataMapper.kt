package com.merseyside.pagingtestapp.data.entity.mapper

import com.merseyside.pagingtestapp.Utils.Companion.decimetresToMetres
import com.merseyside.pagingtestapp.domain.Photos
import com.merseyside.pagingtestapp.domain.Property
import com.merseyside.propertyapi.entity.response.PropertyResponse
import javax.inject.Inject

class PropertyDataMapper @Inject constructor(){

    fun transform(propertyResponse : PropertyResponse) : List<Property> {

        val propertyList = ArrayList<Property>()

        propertyResponse.propertyList.forEach { point->

            val photoList = ArrayList<Photos>()
            point.photos.forEach { photoPoint->
                val photos = Photos(
                        description = photoPoint.description,
                        isCover = photoPoint.isCover,
                        isFacade = photoPoint.isfacade,
                        position = photoPoint.position,
                        sizeExtraUrl = photoPoint.sizeExtraUrl,
                        sizeBigUrl = photoPoint.sizeBigUrl,
                        sizeMediumUrl = photoPoint.sizeMediumUrl,
                        sizeSmallUrl = photoPoint.sizeSmallUrl,
                        sizeExtraSmall = photoPoint.sizeExtraSmall,
                        iconUrl = photoPoint.iconUrl
                )

                photoList.add(photos)
            }

            val property = Property(
                    photoCount = point.photoCount,
                    photoUrls = photoList,
                    price = point.params.price,
                    roomCount = point.params.roomCount,
                    addresses = "${point.params.addresses[0].streetPoint.name}, ${point.params.addresses[0].houseNumber}",
                    totalArea = decimetresToMetres(point.params.totalArea),
                    livingArea = decimetresToMetres(point.params.livingArea),
                    kitchenArea = decimetresToMetres(point.params.kitchenArea),
                    floorsCount = point.params.floorsCount,
                    floor = point.params.floor
            )

            propertyList.add(property)
        }

        return propertyList
    }

}