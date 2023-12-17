package com.wavetest.ajcurrency.domain.data.impl

import android.os.Build
import com.wavetest.ajcurrency.core.service.db.CurrencyDao
import com.wavetest.ajcurrency.core.service.db.RateDao
import com.wavetest.ajcurrency.core.service.db.TimeSaverDao
import com.wavetest.ajcurrency.core.service.retrofit.ApiResponse
import com.wavetest.ajcurrency.core.service.retrofit.CurrencyEndpoint
import com.wavetest.ajcurrency.domain.data.CurrencyRepo
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo
import com.wavetest.ajcurrency.domain.vos.TimeSaverVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class CurrencyRepoImpl @Inject constructor(
    private val currencyEndpoint: CurrencyEndpoint,
    private val currencyDao: CurrencyDao,
    private  val rateDao : RateDao,
    private  val timeSaverDao: TimeSaverDao
) : CurrencyRepo {
    override fun getAllCurrencyFromApi(): Flow<ApiResponse<List<CurrencyVo>?>> = flow {
        emit(ApiResponse.Loading())
        try {
            val result = currencyEndpoint.getCurrencyListByModel()
            val currencyVo = mutableListOf<CurrencyVo>()
            if (result.success) {
                result.currency?.forEach {
                    //     currencyDao.insetSingleCurrency(CurrencyVo(currencyCode=it.key, countryName = "${it.value}" ))
                    currencyVo.add(CurrencyVo(currencyCode = it.key, countryName = "${it.value}"))
                }
                currencyDao.insertCurrency(currencyVo)
                emit(ApiResponse.Success(message = "Success", data = currencyVo))

            } else {
                emit(ApiResponse.Failure(message = result.error?.info ?: "Something Gone Wrong"))
            }
        } catch (error: Exception) {
            emit(ApiResponse.Failure(message = "$error"))
        }
    }


    override fun getAllRateFromApi(): Flow<ApiResponse<List<RateVo>?>> = flow {
        emit(ApiResponse.Loading())
        try {
            val result = currencyEndpoint.getAllRate()
            val rateVo = mutableListOf<RateVo>()
            if (result.success) {

                timeSaverDao.saveTime(timeSaverVo = TimeSaverVo(time = Date()))
                result.rate?.forEach {

                 rateVo.add(RateVo(currencyCode = it.key.drop(3),  price="${it.value}".toDouble()))
                }
                rateDao.insertRate(rateVo)
                emit(ApiResponse.Success(message = "Success", data = rateVo))

            } else {
                emit(ApiResponse.Failure(message = result.error?.info ?: "Something Gone Wrong"))
            }
        } catch (error: Exception) {
            emit(ApiResponse.Failure(message = "$error"))
        }
    }


    override fun getAllCurrencyFromDb(): Flow<List<CurrencyVo>> {
       return  currencyDao.getAllCurrency()
    }

    override fun getAllRateFromDb(): Flow<List<RateVo>> {

        return  rateDao.getAllRate()

    }

    override fun getLaseSavedTime(): Flow<TimeSaverVo> {

        return  timeSaverDao.getTime()
    }
}