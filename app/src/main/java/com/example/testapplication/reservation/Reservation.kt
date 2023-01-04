package com.example.testapplication.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.R
import com.example.testapplication.ui.theme.cuisineColor

@Composable
fun ReservationCard(data: String) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
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
                .fillMaxSize()
                .padding(top = 2.dp)
                .size(
                    width = 353.94.dp,
                    height = 256.29.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    ReservationCard(data = "text")
}