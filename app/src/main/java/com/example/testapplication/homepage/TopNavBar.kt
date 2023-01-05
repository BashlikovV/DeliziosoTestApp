package com.example.testapplication.homepage

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.CartActivity
import com.example.testapplication.MainActivity
import com.example.testapplication.R
import com.example.testapplication.ui.theme.background
import com.example.testapplication.ui.theme.fontPrimary
import com.example.testapplication.ui.theme.fontSecondary

@Composable
fun TopNavBar(name: String) {
    val context = LocalContext.current
    val homeIntent = Intent(context, MainActivity::class.java)
    val cartIntent = Intent(context, CartActivity::class.java)

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 25.dp),
            horizontalArrangement = Arrangement.Start
        ) {
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
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            shape = CircleShape
                        )
                )
            }
            Text(
                text = "Delizi",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = fontPrimary,
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
                .padding(end = 25.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                onClick = {
                    if (name != "CartActivity") {
                        Toast
                            .makeText(context, "Move to cart", Toast.LENGTH_SHORT)
                            .show()
                        Log.i("MYTAG", cartIntent.toString())
                        context.startActivity(cartIntent)
                    } else {
                        Toast
                            .makeText(context, "You in cart", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                shape = CircleShape,
                backgroundColor = background,
                modifier = Modifier
                    .padding(
                        top = 35.dp
                    )
                    .height(24.dp)
                    .width(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Cart"
                )
            }
            FloatingActionButton(
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
                backgroundColor = background,
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 20.dp
                    )
                    .height(26.dp)
                    .width(26.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    TopNavBar("MainActivity")
}