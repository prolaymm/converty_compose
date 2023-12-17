package com.wavetest.ajcurrency.domain.data

import com.wavetest.ajcurrency.core.service.retrofit.ApiResponse
import com.wavetest.ajcurrency.domain.vos.CurrencyRateResponseVo
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo
import com.wavetest.ajcurrency.domain.vos.TimeSaverVo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    fun  getAllCurrencyFromApi() : Flow<ApiResponse<List<CurrencyVo>?>>
    fun  getAllRateFromApi() : Flow<ApiResponse<List<RateVo>?>>
    fun getAllCurrencyFromDb() : Flow<List<CurrencyVo>>
    fun getAllRateFromDb() : Flow<List<RateVo>>
    fun getLaseSavedTime() : Flow<TimeSaverVo>
}