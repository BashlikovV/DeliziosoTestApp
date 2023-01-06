package com.example.testapplication.footer

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.MainActivity
import com.example.testapplication.R
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.fontSecondary
import com.example.testapplication.ui.theme.white

@Composable
fun Footer(name: String) {
    val context = LocalContext.current
    val homeIntent = Intent(context, MainActivity::class.java)

    val toast: (String) -> Unit = {
        Toast
            .makeText(context, it, Toast.LENGTH_SHORT)
            .show()
    }
    
    Column(
        modifier = Modifier
            .background(cuisineColor)
            .fillMaxSize(1f)
            .padding(start = 25.dp, bottom = 25.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            FloatingActionButton (
                onClick = {
                    if (name != "MainActivity") {
                        Toast
                            .makeText(context, "Move to home", Toast.LENGTH_SHORT)
                            .show()
                        context.startActivity(homeIntent)
                    } else {
                        Toast
                            .makeText(context, "You in homepage", Toast.LENGTH_SHORT)
                            .show()

                    }
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(
                        top = 25.dp,
                        bottom = 25.dp
                    )
                    .height(51.dp)
                    .width(51.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "BtnImage",
                )
            }
            Text(
                text = "Delizi",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 25.dp,
                        top = 38.dp
                    )
            )
            Text(
                text = "oso",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = fontSecondary,
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = 38.dp
                    )
            )
        }
        Row(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(id = R.string.lorem_ipsum),
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .align(Alignment.Start)
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Start,
        ) {
            Column(
                Modifier.padding(end = 25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grouptwitter),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(
                            height = 40.dp,
                            width = 40.dp
                        )
                )
            }
            Column(
                Modifier.padding(end = 25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(
                            height = 40.dp,
                            width = 40.dp
                        )
                )
            }
            Column {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(
                            height = 40.dp,
                            width = 40.dp
                        )
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 69.dp)
        ) {
            PrimaryFooterText(str = "Page") {toast(it)}
            FooterText(str = "Home") {toast(it)}
            FooterText(str = "Menu") {toast(it)}
            FooterText(str = "Order online") {toast(it)}
            FooterText(str = "Catering") {toast(it)}
            FooterText(str = "Reservation") {toast(it)}

            PrimaryFooterText(str = "Reservation") {toast(it)}
            FooterText(str = "About us") {toast(it)}
            FooterText(str = "Testimonial") {toast(it)}
            FooterText(str = "Event") {toast(it)}

            PrimaryFooterText(str = "Get in touch") {toast(it)}
            FooterText(str = "3247 Johnson Ave, Bronx, NY 10463, Amerika Serikat") {toast(it)}
            FooterText(str = "delizioso@gmail.com") {toast(it)}
            FooterText(str = "+123 4567 8901") {toast(it)}
            Spacer(modifier = Modifier.padding(bottom = 35.dp))

            FooterText(str = "Copyright c 2022 Delizioso") {toast(it)}
        }
    }
}

@Composable
fun PrimaryFooterText(str: String, onClick: (String) -> Unit) {
    Row {
        TextButton (
            onClick = {
                onClick(str)
            },
            modifier = Modifier
                .padding(bottom = 25.dp)
        ) {
            Text(
                text = str,
                color = fontSecondary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun FooterText(str: String, onClick: (String) -> Unit) {
    Row {
        TextButton (
            onClick = {
                onClick(str)
            }
        ) {
            Text(
                text = str,
                color = white,
                fontSize = 14.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    Footer(name = "MainActivity")
}