package com.example.testapplication.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.ui.theme.background
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.navItemColor

@Composable
fun MenuContent(
    name: String
) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState()
            )
            .fillMaxSize(fraction = 1f)
    ) {
        TopNavBar(name = name)
        Menu()
        Footer(name = name)
    }
}

private fun menuHeaderTextConstraints(): ConstraintSet {
    return ConstraintSet {
        val menuHeaderText = createRefFor("menuHeaderText")
        val menuNavigationBar = createRefFor("menuNavigationBar")
        val menuNavHost = createRefFor("menuNavHost")

        constrain(menuHeaderText) {
            top.linkTo(anchor = parent.top)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
            bottom.linkTo(anchor = menuNavigationBar.top)
        }
        constrain(menuNavigationBar) {
            top.linkTo(anchor = menuHeaderText.bottom)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
            bottom.linkTo(anchor = menuNavHost.top)
        }
        constrain(menuNavHost) {
            top.linkTo(anchor = menuNavigationBar.bottom)
//            start.linkTo(anchor = parent.start)
//            end.linkTo(anchor = parent.end)
        }
    }
}

@Composable
fun Menu() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(fraction = 1f)
    ) {
        val constraints = menuHeaderTextConstraints()

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .fillMaxSize(fraction = 1f)
        ) {
            Text(
                text = "Menu",
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = cuisineColor,
                modifier = Modifier
                    .layoutId("menuHeaderText")
            )
            MenuTopNavigationBar()
        }
    }
}

@Composable
fun MenuTopNavigationBar() {
    val navBarItems = listOf("All category", "Dinner", "Lunch", "Dessert")
    val navController = rememberNavController()
    var selectedItem by remember {
        mutableStateOf(navBarItems[0])
    }

    NavigationBar(
        contentColor = background,
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .layoutId("menuNavigationBar")
    ) {
        navBarItems.forEach { navBarItem ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedItem = navBarItem
                    navController.navigate(navBarItem)
                },
                label = {
                    val color = if (selectedItem == navBarItem) {
                        Color.White
                    } else {
                        cuisineColor
                    }
                    val backgroundColor = if (selectedItem != navBarItem) {
                        navItemColor
                    } else {
                        cuisineColor
                    }

                    Text(
                        text = navBarItem,
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

    MenuTopBarNavHost(navigationController = navController, navBarItems = navBarItems)
}

@Composable
fun MenuTopBarNavHost(
    navigationController: NavHostController,
    navBarItems: List<String>
) {
    NavHost(
        navController = navigationController,
        startDestination = navBarItems[0],
        modifier = Modifier
            .layoutId("menuNavHost")
    ) {
        composable(navBarItems[0]) {
            MenuFirstScreen()
        }
        composable(navBarItems[1]) {
            MenuSecondScreen()
        }
        composable(navBarItems[2]) {
            MenuThirdScreen()
        }
        composable(navBarItems[3]) {
            MenuFourthScreen()
        }
    }
}

@Preview
@Composable
fun MenuContentPreview() {
    MenuContent(name = "MenuActivity")
}