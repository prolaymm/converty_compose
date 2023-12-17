package com.wavetest.ajcurrency.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import android.view.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wavetest.ajcurrency.core.make3Digit
import com.wavetest.ajcurrency.core.service.retrofit.ApiResponse
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo
import com.wavetest.ajcurrency.presentation.components.CustomDropDown
import com.wavetest.ajcurrency.presentation.components.CustomTextField
import com.wavetest.ajcurrency.ui.theme.PrimaryColor
import com.wavetest.ajcurrency.view_model.CurrencyExchangeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(currencyViewModel: CurrencyExchangeViewModel = hiltViewModel()) {
    val expression = Regex("^[0-9.]+\$")
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableStateOf<RateVo?>(null)
    }
    var textController by remember {
        mutableStateOf("")
    }

    val list = currencyViewModel.getRateList.collectAsState()
    val rateList = currencyViewModel.convertedRateList.collectAsState()

    Scaffold(topBar = {

        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = PrimaryColor
            ),
            title = {
                Text("Converty", color = Color.White)
            }) /// CenterAlignedTopAppBar
    }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            CustomTextField(
                textController = textController,
                hintText = "Enter digit",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                if (it.isEmpty() || it.matches(expression)) textController = it

            } ///CustomTextField


            if (list.value.isNotEmpty()) Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Surface(modifier = Modifier.width(200.dp)) {
                    CustomDropDown(
                        rateVo = list.value,
                        selectedCurrency = selectedItem,
                        onChangeExpand = {
                            isExpanded = it
                        },
                        isExpand = isExpanded,
                        onDismiss = {
                            isExpanded = false
                        }) {
                        selectedItem = it
                        val count = if (textController.isEmpty()) "0.0" else textController
                        currencyViewModel.onConvertRate(selectedItem, inputValue = count.toDouble())
                        isExpanded = false
                    } ///customdrowpdown

                } ///surface
                Spacer(modifier = Modifier.width(8.dp)) //Spacer
                Box(

                    modifier = Modifier
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(6.dp),


                    ) {

                    Text(
                        text = "1 USD = ${(selectedItem?.price ?: 1.00)} ${selectedItem?.currencyCode ?: "USD"}",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.Gray
                    ) //Text

                } ///Box
            } else CircularProgressIndicator()



            LazyColumn(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                items(rateList.value) { vo ->

                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .height(40.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(vo.currencyCode, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("${vo.price}")
                    } ///Row
                    Divider(color = Color.Gray, thickness = 0.4.dp)

                }

            } ///LazyColumn


        } ///column
    } ///scaffold


}
