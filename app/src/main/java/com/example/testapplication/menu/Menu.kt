package com.example.testapplication.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.testapplication.ui.theme.background
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.navItemColor

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
        Menu()
        Footer(name = name)
    }
}

@Composable
fun Menu() {
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
            color = cuisineColor
        )

        FirstMenu()
    }
}

@Composable
fun FirstMenu() {
    val items = listOf("All category", "Dinner", "Lunch", "Dessert")
    val navController = rememberNavController()
    var selectedItem by remember {
        mutableStateOf(items[1])
    }

    NavigationBar(
        contentColor = navItemColor,
        containerColor = background
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedItem = screen
                    navController.navigate(screen)
                },
                label = {
                    val color = if (selectedItem == screen) {
                        Color.White
                    } else {
                        cuisineColor
                    }
                    val backgroundColor = if (selectedItem != screen) {
                        navItemColor
                    } else {
                        cuisineColor
                    }

                    Text(
                        text = screen,
                        color = color,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(backgroundColor)
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 21.dp,
                                end = 21.dp
                            ),
                        maxLines = 1
                    )
                },
                icon = {
                    
                }
            )
        }
    }

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