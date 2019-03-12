package com.merseyside.propertyapi.net

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merseyside.propertyapi.R
import com.merseyside.propertyapi.entity.response.PropertyResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PropertyResponseCreator(context : Context) {

    private var propertyRest : PropertyRest

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(createConverterFactory()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        propertyRest = retrofit.create(PropertyRest::class.java)
    }

    private fun createConverterFactory(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    fun getProperties(limit : Int, offset : Int) : Single<PropertyResponse> {
        return propertyRest.getProperties(limit, offset)
    }
}