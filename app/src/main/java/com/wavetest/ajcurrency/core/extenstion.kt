package com.wavetest.ajcurrency.core


fun String.make3Digit(): Double {
    return String.format("%.3f", this).toDouble()
}