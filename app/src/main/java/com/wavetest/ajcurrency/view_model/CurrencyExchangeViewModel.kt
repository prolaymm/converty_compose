package com.wavetest.ajcurrency.view_model

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavetest.ajcurrency.core.make3Digit
import com.wavetest.ajcurrency.core.service.retrofit.ApiResponse
import com.wavetest.ajcurrency.domain.data.impl.CurrencyRepoImpl
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(private val repoImpl: CurrencyRepoImpl) :
    ViewModel() {

    private val _currencyList = MutableStateFlow<List<CurrencyVo>>(emptyList())

    private val _rateList = MutableStateFlow<List<RateVo>>(emptyList())
    private val _currencyApiResult =
        MutableStateFlow<ApiResponse<List<CurrencyVo>?>>(ApiResponse.Loading())
     val rateApiResult = MutableStateFlow<ApiResponse<List<RateVo>?>>(ApiResponse.Loading())

    fun getCurrencyApiResult(): StateFlow<ApiResponse<List<CurrencyVo>?>> = _currencyApiResult
    fun getCurrencyList(): StateFlow<List<CurrencyVo>> = _currencyList
    val getRateList: StateFlow<List<RateVo>> get() = _rateList
    var convertedRateList = MutableStateFlow<List<RateVo>>(emptyList())


    init {
        Log.d("arjun","working view model")
       getLastedSavedTime()
    }
    private fun getAllCurrencyFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repoImpl.getAllCurrencyFromDb().collect {
                _currencyList.value = it
            }
        }

    }

    private fun getAllRateFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repoImpl.getAllRateFromDb().collect {
                _rateList.value = it
            }
        }

    }

    private fun getCurrencyFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            repoImpl.getAllCurrencyFromApi().collect {
                _currencyApiResult.value = it
            }
        }
    }

    private fun getRateFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            repoImpl.getAllRateFromApi().collect {
                rateApiResult.value = it
            }
        }


    }



    fun onConvertRate(currencyVo: RateVo? = RateVo("USD", 1.00), inputValue: Double) {
        Log.d("arjun", "selected ${currencyVo?.currencyCode}")

        viewModelScope.launch(Dispatchers.IO) {
            if (currencyVo?.currencyCode == "USD") {
                convertedRateList.value = _rateList.value.map {
                    val price: Double = "${it.price * inputValue}".make3Digit()
                    RateVo(it.currencyCode, price)
                }
            } else {
                convertedRateList.value = _rateList.value.map {
                    val price: Double = String.format(
                        "%.3f",
                        (1 / (currencyVo?.price ?: 0.00007)) * inputValue * it.price
                    ).toDouble()
                    ///  if(it.currencyCode == currencyVo?.currencyCode)  RateVo("USD",price="${it.price*inputValue}".make3Digit()) else
                    RateVo(it.currencyCode, price)
                }

            }

        }


    }
 private   fun fetchFromApi(){
        getCurrencyFromApi()
        getRateFromApi()
    }
   private fun fetchFromDb(){
        getAllCurrencyFromDb()
        getAllRateFromDb()
    }



      fun getLastedSavedTime() {

       viewModelScope.launch(Dispatchers.IO) {
           repoImpl.getLaseSavedTime().catch {

           }.collect{

               if("$it" == "null") {
                   Log.d("arjun","if success ${it}")
                   fetchFromApi()
                   fetchFromDb()
               } else {
                   val currentDate = Date()
                   val diffInMillis = currentDate.time - it.time!!.time
                   val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
                   val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) % 24
                   val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60
                   if(days>0 || hours>0 || minutes>30 ) {
                       Log.d("arjun","greater than 30")
                       fetchFromApi()
                       fetchFromDb()
                   } else {
                       fetchFromDb()
                   }
               }

           }
       }
    }
}


//if(days>0 || hours>0 || minutes>30 )