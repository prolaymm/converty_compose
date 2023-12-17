package com.wavetest.ajcurrency.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo

@Composable
fun CustomDropDownMenu() {

    Row {

    }

}
/*{
    isExpand = it
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDown(rateVo :List<RateVo>,selectedCurrency: RateVo?,isExpand : Boolean,onDismiss:()-> Unit, onChangeExpand : (Boolean) -> Unit,onChangeSelectedItem: (RateVo) -> Unit,) {

    ExposedDropdownMenuBox(expanded = isExpand, onExpandedChange = {
        onChangeExpand(it)
    }) {
        TextField(value = if(selectedCurrency == null)"Select currency" else "${selectedCurrency.currencyCode}",
            shape = RoundedCornerShape(12.dp),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpand)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .menuAnchor()
                .focusProperties {
                    canFocus = false
                }
        )

        ExposedDropdownMenu(expanded = isExpand, onDismissRequest = onDismiss) {
           rateVo.forEach{
               DropdownMenuItem(text = { Text("${it.currencyCode}") }, onClick ={
                   onChangeSelectedItem(it)
               })
           }

        }
    }
}
