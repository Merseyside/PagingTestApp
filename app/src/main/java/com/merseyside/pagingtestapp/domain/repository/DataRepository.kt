package com.merseyside.pagingtestapp.domain.repository

import com.merseyside.pagingtestapp.domain.Property
import io.reactivex.Single

interface DataRepository {

    fun getProperties(limit : Int, offset : Int) : Single<List<Property>>
}