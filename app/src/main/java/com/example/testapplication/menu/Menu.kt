package com.example.testapplication.menu

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.R
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.HeadBody
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.homepage.WelcomeBody
import com.example.testapplication.ui.theme.background
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.fontSecondary
import com.example.testapplication.ui.theme.navItemColor
import com.example.testapplication.view.model.MenuActivityViewModel

@Composable
fun MenuContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState()
            )
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
        mutableStateOf(items[0])
    }

    NavigationBar(
        contentColor = navItemColor,
        containerColor = background,
        modifier = Modifier,
        content = {
            Row {
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
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        icon = {}
                    )
                }
            }
        }
    )

    NavHost(navController = navController, items = items)
}

@Composable
fun NavHost(navController: NavHostController, items: List<String>) {
    NavHost(navController = navController, startDestination = items[0]) {
        composable(items[0]) {
            NavScreen(items, itemName = items[0])
        }
        composable(items[1]) {
            NavScreen(items, itemName = items[1])
        }
        composable(items[2]) {
            NavScreen(items, itemName = items[2])
        }
        composable(items[3]) {
            NavScreen(items, itemName = items[3])
        }
    }
}

@Composable
fun NavScreen(items: List<String>, itemName: String) {
    when(itemName) {
        items[0] -> ProductCardsLayout()
        items[1] -> WelcomeBody()
        items[2] -> HeadBody()
        items[3] -> WelcomeBody()
    }
}

@Composable
fun ProductCardsLayout() {
    val cart = MenuActivityViewModel().testData

    var selectedItem by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .background(background)
            .padding(
                top = 15.dp,
                start = 25.dp,
                end = 25.dp
            )
            .fillMaxSize(1f),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(bottom = 25.dp)
        ) {
            Column {
                Row {
                    Text(
                        text = "Pasta",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = cuisineColor,
                        modifier = Modifier
                            .padding(
                                top = 30.dp
                            )
                    )
                }
                Row {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .width(74.dp)
                            .background(fontSecondary)
                            .padding(top = 5.dp)
                    )
                }
            }
        }
        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 275.dp),
            modifier = Modifier
                .height(860.dp)
                .width(700.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            userScrollEnabled = false,
            content = {
                items(cart) { cartItem ->
                    ProductCard(
                        image = cartItem.imageValue,
                        productName = cartItem.productNameValue,
                        productCost = cartItem.productCostValue,
                        starsCount = cartItem.starsCountValue,
                        isSelected = cartItem.index == selectedItem,
                        //When index of selected item == item index, send true
                        count = cartItem.countValue,
                        index = cartItem.index,
                        //Send item index to change outside selected item index
                        onClick = {
                            selectedItem = it
                            //Change selected item index
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun ProductCard(
    image: @Composable () -> Unit,
    productName: String,
    productCost: Float,
    starsCount: Int,
    isSelected: Boolean,
    count: Int,
    index: Int,
    onClick: (Int) -> Unit
) {
    val backgroundColor = if (isSelected) {
        fontSecondary
    } else {
        navItemColor
    }
    val textColor = if (isSelected) {
        navItemColor
    } else {
        cuisineColor
    }
    val noColor = if (isSelected) {
        navItemColor
    } else {
        fontSecondary
    }

    var state by remember {
        mutableStateOf(count)
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(38.2.dp))
            .background(backgroundColor)
            .width(165.dp)
            .clickable(onClick = {
                onClick(index)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 13.dp,
                    start = 21.dp,
                    end = 21.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                image()
            }
            Row {
                Text(
                    text = productName,
                    color = textColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(
                            top = 10.dp
                        )
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 0 until starsCount) {
                    Image(
                        painter = painterResource(id = R.drawable.star_filled),
                        contentDescription = "Star ${i + 1}",
                        modifier = Modifier
                            .size(9.14.dp)
                            .padding(
                                end = 3.7.dp
                            )
                    )
                }
                for (i in starsCount..4) {
                    Image(
                        painter = painterResource(id = R.drawable.star_white_border),
                        contentDescription = "Star ${i + 1}",
                        modifier = Modifier
                            .size(9.14.dp)
                            .padding(
                                end = 3.7.dp
                            )
                    )
                }
            }
            Row {
                Text(
                    text = stringResource(id = R.string.lorem_ipsum),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .size(width = 130.dp, height = 30.dp),
                )
            }
            Row {
                Column {
                    Text(
                        text = "$$productCost",
                        maxLines = 1,
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                top = 15.dp,
                                end = 41.dp
                            )
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 9.5.dp)
                ) {
                    FloatingActionButton(
                        onClick = {
                            state++
                            onClick(index)
                        },
                        backgroundColor = noColor,
                        modifier = Modifier
                            .size(33.dp),
                    ) {
                        ViewElem(state = state)
                    }
                }
            }
        }
    }
}

@Composable
fun ViewElem(state: Int) {
    if (state == 0) {
        Image(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "Add product",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(33.dp)
        )
    } else {
        Text(
            text = "${state}x",
            color = cuisineColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    MenuContent(name = "CartActivity")
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ProductCard(
        image = {
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        },
        productName = "Spaghetti",
        productCost = 12.05f,
        starsCount = 4,
        isSelected = true,
        count = 0,
        index = 0
    ) {}
}