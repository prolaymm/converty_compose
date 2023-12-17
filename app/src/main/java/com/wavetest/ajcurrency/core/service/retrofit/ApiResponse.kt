package com.wavetest.ajcurrency.core.service.retrofit

sealed  class ApiResponse<T>
    (val data : T? = null,
     val message : String? = null) {
    class Success<T> ( data : T?,message: String? = "Success") : ApiResponse<T>(data = data,message = message)
    class Failure<T> (message : String) : ApiResponse<T>(message=message)
    class Loading<T>(message: String?="Loading")  : ApiResponse<T>(message=message)
}