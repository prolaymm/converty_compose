package com.wavetest.ajcurrency.domain.vos
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class CurrencyResponse (

    @SerializedName("success" ) var success : Boolean = false,
    @SerializedName("error") var error   : CurrencyErrorVo?   = null,
    @SerializedName("currencies") var currency : Map<String,*>? = null,
)