package com.merseyside.pagingtestapp.data.entity.mapper

import com.merseyside.pagingtestapp.utils.Utils.Companion.decimetresToMetres
import com.merseyside.pagingtestapp.domain.Photos
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.propertyapi.entity.response.PropertyResponse
import javax.inject.Inject

class PropertyDataMapper @Inject constructor(){

    fun transform(propertyResponse : PropertyResponse) : List<PropertyModel> {

        val propertyList = ArrayList<PropertyModel>()

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

            val property = PropertyModel(
                    id = point.id,
                    photoCount = point.photoCount,
                    photoUrls = photoList,
                    price = point.params.price,
                    roomCount = point.params.roomCount,
                    address = (point.params.addresses?.get(0)?.streetPoint?.name ?: "Без адреса") +
                            ", ${point.params.addresses?.get(0)?.houseNumber ?: ""}",
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