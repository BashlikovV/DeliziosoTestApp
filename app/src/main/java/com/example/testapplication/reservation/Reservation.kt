package com.example.testapplication.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.R
import com.example.testapplication.ReservationActivity
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.inputColor

@Composable
fun ReservationCard(data: String) {
    fun saveInputData(index: Int, str: String) {
        when(index) {
            0 -> {
                ReservationActivity.Data.date = str
            }
            1 -> {
                ReservationActivity.Data.time = str
            }
            2 -> {
                ReservationActivity.Data.partySize = str
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopNavBar(name = data)
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
        GetText(value = "Date") {
            saveInputData(0, it)
        }
        GetText(value = "Time") {
            saveInputData(1, it)
        }
        GetText(value = "Party size") {
            saveInputData(2, it)
        }
        Footer(name = data)
    }
}

@Composable
fun GetText(value: String, listener: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(bottom = 25.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                listener(text.text)
            },
            label = {
                Text(
                    text = value,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(inputColor),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    ReservationCard(data = "text")
}