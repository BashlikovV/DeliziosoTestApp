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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.fontSecondary

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
fun MenuFirstScreenContent(
    menuViewModel: MenuViewModel = viewModel()
) {
    val menuUiState: State<List<MenuUiState>> = menuViewModel.menuUiState.collectAsState()

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
        items(menuUiState.value) { menuItem ->
            ProductView(
                image = menuItem.currentImageValue,
                productName = menuItem.currentNameValue,
                productCost = menuItem.currentCostValue,
                starsCount = menuItem.currentStarsValue,
                isSelected = menuItem.index == menuViewModel.selectedItem,
                count = menuItem.currentCountValue,
                index = menuItem.index,
                onClick = { selectedItemIndex, count ->
                    menuViewModel.onSelectItem(selectedItemIndex)
                    menuViewModel.onCountChanged(selectedItemIndex, count)
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