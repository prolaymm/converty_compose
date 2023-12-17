package com.wavetest.ajcurrency.core.service.retrofit

import com.wavetest.ajcurrency.domain.vos.CurrencyRateResponseVo
import com.wavetest.ajcurrency.domain.vos.CurrencyResponse
import com.wavetest.ajcurrency.utils.ApiConstants
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyEndpoint {

    @GET(ApiConstants.live)
    suspend fun  getAllRate() : CurrencyRateResponseVo


    @GET(ApiConstants.list)
    suspend fun  getCurrencyListByModel() : CurrencyResponse
}