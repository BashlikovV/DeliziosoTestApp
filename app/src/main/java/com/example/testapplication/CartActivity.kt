package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapplication.cart.CartContent
import com.example.testapplication.ui.theme.TestApplicationTheme
import com.example.testapplication.ui.theme.background

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = background,
                ) {
                    CartContent(name = this.localClassName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = background
        ) {
            CartContent(name = "CartActivity")
        }

    }
}