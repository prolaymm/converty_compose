package com.wavetest.ajcurrency

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import com.wavetest.ajcurrency.core.service.db.CurrencyDao
import com.wavetest.ajcurrency.core.service.retrofit.CurrencyEndpoint
import com.wavetest.ajcurrency.domain.data.impl.CurrencyRepoImpl
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.presentation.screens.HomeScreen
import com.wavetest.ajcurrency.ui.theme.CurrencyExchangeTheme
import com.wavetest.ajcurrency.view_model.CurrencyExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {




    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyExchangeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  Column {
                      HomeScreen()

                  }
                }
            }
        }
    }
}





