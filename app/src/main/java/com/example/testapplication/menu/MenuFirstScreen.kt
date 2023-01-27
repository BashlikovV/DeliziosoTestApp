package com.example.testapplication.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.testapplication.R
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.fontSecondary
import com.example.testapplication.view.model.MenuActivityViewModel

private fun menuFirstScreenConstraints(): ConstraintSet {
    return ConstraintSet {
        val menuGroupNameText = createRefFor("menuGroupNameText")
        val menuGroupNameSpacer = createRefFor("menuGroupNameSpacer")
        val lazyFirstScreenContent = createRefFor("lazyFirstScreenContent")

        constrain(menuGroupNameText) {
            start.linkTo(anchor = parent.start)
        }
        constrain(menuGroupNameSpacer) {
            top.linkTo(anchor = menuGroupNameText.bottom, margin = 5.dp)
            start.linkTo(anchor = parent.start)
        }
        constrain(lazyFirstScreenContent) {
            top.linkTo(anchor = menuGroupNameSpacer.bottom, margin = 25.dp)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
        }
    }
}
@Composable
fun MenuFirstScreen() {
    Column(
        modifier = Modifier
            .height(860.dp)
            .width(700.dp)
            .layoutId("firstScreenContent")
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(fraction = 1f)
        ) {
            val constraints = menuFirstScreenConstraints()

            ConstraintLayout(
                constraintSet = constraints,
                modifier = Modifier
                    .fillMaxSize(fraction = 1f)
                    .padding(
                        start = 25.dp,
                        end = 25.dp,
                        top = 30.dp,
                        bottom = 50.dp
                    )
            ) {
                Text(
                    text = "Pasta",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = cuisineColor,
                    modifier = Modifier
                        .layoutId("menuGroupNameText")
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .width(74.dp)
                        .background(fontSecondary)
                        .layoutId("menuGroupNameSpacer")
                )
                MenuFirstScreenContent()
            }
        }
    }
}

@Composable
fun MenuFirstScreenContent() {
    //TODO("Remade to ViewModel and LiveData. Test solution!")
    val menuItems = MenuActivityViewModel().testData

    var selectedItem by remember {
        mutableStateOf(0)
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        state = rememberLazyGridState(0),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        userScrollEnabled = false,
        modifier = Modifier
            .height(860.dp)
            .width(700.dp)
            .layoutId("lazyFirstScreenContent")
    ) {
        items(menuItems) { menuItem ->
            ProductView(
                //TODO("Change menu Items in ViewModel.")
                image = R.drawable.illustration,
                productName = menuItem.productNameValue,
                productCost = menuItem.productCostValue,
                starsCount = menuItem.starsCountValue,
                isSelected = menuItem.index == selectedItem,
                count = menuItem.countValue,
                index = menuItem.index,
                onClick = { selectedItemIndex, count ->
                    selectedItem = selectedItemIndex
                    menuItems[selectedItemIndex].countValue = count
                }
            )
        }
    }
}

@Preview
@Composable
fun MenuFirstScreenPreview() {
    MenuFirstScreen()
}