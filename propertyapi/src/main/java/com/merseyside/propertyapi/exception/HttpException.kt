package com.merseyside.propertyapi.exception

class HttpException(message : String? = "", val code : Int) : Exception(message)