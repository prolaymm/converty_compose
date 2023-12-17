package com.wavetest.ajcurrency.domain.vos

import com.google.gson.annotations.SerializedName

data class CurrencyRateResponseVo (@SerializedName("success" ) var success : Boolean = false,
                                   @SerializedName("error") var error   : CurrencyErrorVo?   = null,
                                   @SerializedName("quotes") var rate : Map<String,*>? = null,)