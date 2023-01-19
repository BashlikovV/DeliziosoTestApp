package com.example.testapplication.homepage

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.OrderActivity
import com.example.testapplication.R
import com.example.testapplication.ReservationActivity
import com.example.testapplication.footer.Footer
import com.example.testapplication.ui.theme.*

@Composable
fun HeadBtn() {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(
                start = 25.dp,
                top = 25.dp
            )
    ) {
        TextButton(
            onClick = {
                Toast
                    .makeText(context, "Restaurants", Toast.LENGTH_SHORT)
                    .show()
            },
            colors = ButtonDefaults.buttonColors(restBackground),
            shape = RoundedCornerShape(60)
        ) {
            Text(
                "Restaurants",
                color = fontSecondary
            )
        }
    }
}

@Composable
fun ReservationButton(content: String) {
    val context = LocalContext.current
    var color = fontSecondary
    if (content[0] == 'R') {
        color = reservationColor
    }
    val reservationIntent = Intent(context, ReservationActivity::class.java)
    val orderIntent = Intent(context, OrderActivity::class.java)

    TextButton(
        onClick = {
            Toast
                .makeText(context, content, Toast.LENGTH_SHORT)
                .show()
            if (content[0] == 'R') {
                context.startActivity(reservationIntent)
            } else {
                context.startActivity(orderIntent)
            }
        },
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(60),
        modifier = Modifier
            .height(44.55.dp)
            .width(139.1.dp)
    ) {
        Text(
            content,
            fontSize = 11.97.sp,
            color = white,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun HeadBody() {
    Column (
        modifier = Modifier
            .padding(
                top = 25
                    .dp,
                start = 25.dp,
                end = 25.dp
            )
    ) {
        Text(
            text = "Italian Cuisine",
            fontWeight = FontWeight.Bold,
            color = cuisineColor,
            fontSize = 60.sp
        )
        Text(
            text = stringResource(R.string.lorem_ipsum),
            fontSize = 12.sp,
            color = lorColor
        )
        Row(
            modifier = Modifier
                .padding(top = 45.dp)
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ReservationButton(content = "Order now")
            ReservationButton(content = "Reservation")
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = "Illustration",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
                    .size(
                        width = 368.01.dp,
                        height = 315.26.dp
                    )
            )
        }
    }

}

@Composable
fun WelcomeBody() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(welcomeBackground)
            .graphicsLayer(alpha = 10f)
            .fillMaxSize(1f)
            .fillMaxHeight(1f)
            .padding(
                top = 75.dp,
                start = 25.dp
            )
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                text = "Welcome to",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = cuisineColor,
                textAlign = TextAlign.Start
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "delizioso",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = fontSecondary,
                textAlign = TextAlign.Start
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(top = 25.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lorem_ipsum),
                fontSize = 12.sp,
                color = lorColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .size(
                        width = 291.dp,
                        height = 96.dp
                    )
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(top = 25.dp)
        ) {
            TextButton(
                onClick = {
                    Toast
                        .makeText(context, "See our menu", Toast.LENGTH_SHORT)
                        .show()
                },
                colors = ButtonDefaults.buttonColors(fontSecondary),
                shape = RoundedCornerShape(60),
                modifier = Modifier
                    .size(
                        width = 168.dp,
                        height = 55.dp
                    )
            ) {
                Text(
                    "See our menu",
                    fontSize = 14.sp,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.picture),
                contentDescription = "Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .size(
                        width = 367.12.dp,
                        height = 357.86.dp
                    )
            )
        }
    }
}

@Composable
fun HomeContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        TopNavBar(name = name)
        HeadBtn()
        HeadBody()
        WelcomeBody()

        Footer(name = name)
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    HomeContent("MainActivity")
}