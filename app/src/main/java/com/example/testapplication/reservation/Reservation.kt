package com.example.testapplication.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.R
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.inputColor
import com.example.testapplication.ui.theme.lorColor
import com.example.testapplication.view.model.ReservationActivityViewModel

@Composable
fun ReservationCard(
    name: String,
    reservationActivityViewModel: ReservationActivityViewModel
) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopNavBar(name = name)
        Row {
            Text(
                text = "Book a table",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = cuisineColor
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.component),
                contentDescription = "Component",
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(top = 30.dp, bottom = 30.dp)
                    .size(
                        width = 353.94.dp,
                        height = 256.29.dp
                    )
                    .offset(x = (-60).dp),
                contentScale = ContentScale.FillHeight,
            )
        }
        GetText(value = ReservationActivityViewModel.DATA) {
            reservationActivityViewModel.saveReservationActivityData(0, it)
        }
        GetText(value = ReservationActivityViewModel.TIME) {
            reservationActivityViewModel.saveReservationActivityData(1, it)
        }
        GetText(value = ReservationActivityViewModel.PARTY_SIZE) {
            reservationActivityViewModel.saveReservationActivityData(2, it)
        }
        Footer(name = name)
    }
}

@Composable
fun GetText(
    value: String,
    listener: (String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(bottom = 25.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                listener(text)
            },
            label = {
                Text(
                    text = value,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(inputColor),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(lorColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    ReservationCard(name = "text", ReservationActivityViewModel())
}