package com.wavetest.ajcurrency.domain.vos
import com.google.gson.annotations.SerializedName

data class CurrencyErrorVo (

    @SerializedName("code" ) var code : Int?    = null,
    @SerializedName("type" ) var type : String? = null,
    @SerializedName("info" ) var info : String? = null

)