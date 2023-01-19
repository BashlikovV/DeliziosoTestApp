package com.example.testapplication.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar

@Composable
fun MenuContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize()
    ) {
        TopNavBar(name = name)
        Menu(name = name)
        Footer(name = name)
    }
}

@Composable
fun Menu(
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Menu",
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
        )

        FirstMenu()
    }
}

@Composable
fun FirstMenu() {
    val items = listOf("All category", "Dinner", "Lunch", "Dessert")
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = items[0]) {
        composable(items[0]) {
            Text(
                text = items[0],
                modifier = Modifier
                    .padding(25.dp)
            )
        }
        composable(items[1]) {
            Text(
                text = items[1],
                modifier = Modifier
                    .padding(25.dp)
            )
        }
        composable(items[2]) {
            Text(
                text = items[2],
                modifier = Modifier
                    .padding(25.dp)
            )
        }
        composable(items[3]) {
            Text(
                text = items[3],
                modifier = Modifier
                    .padding(25.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    MenuContent(name = "CartActivity")
}