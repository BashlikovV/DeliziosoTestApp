package com.example.testapplication.cart

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.testapplication.MenuActivity
import com.example.testapplication.R
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.menu.MenuActivityData
import com.example.testapplication.ui.theme.background
import com.example.testapplication.ui.theme.cartBackBackground
import com.example.testapplication.ui.theme.cartItemBackground
import com.example.testapplication.ui.theme.fontPrimary
import com.example.testapplication.ui.theme.fontSecondary

@Composable
fun CartContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize()
    ) {
        TopNavBar(name = name)
        CartToBack()
        CartList(cart = MenuActivityData)
        Footer(name = name)
    }
}

private fun cartToBackConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val backImage = createRefFor("backImage")
        val orderListText = createRefFor("orderListText")

        constrain(backImage) {
            start.linkTo(anchor = parent.start, margin = margin)
            top.linkTo(anchor = parent.top, margin = margin)
            bottom.linkTo(anchor = parent.bottom, margin = margin)
        }
        constrain(orderListText) {
            end.linkTo(anchor = parent.end)
            start.linkTo(anchor = parent.start)
            top.linkTo(anchor = parent.top)
            bottom.linkTo(anchor = parent.bottom)
        }
    }
}

@Composable
fun CartToBack() {
    val context = LocalContext.current

    BoxWithConstraints {
        val constraints = cartToBackConstraints(25.dp)

        ConstraintLayout(
            constraints,
            modifier = Modifier
                .background(cartBackBackground)
                .fillMaxWidth(1f)
                .height(80.dp)
                .clickable {
                    context.startActivity(Intent(context, MenuActivity::class.java))
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrowback),
                contentDescription = "Move to back",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(cartItemBackground)
                    .layoutId("backImage")
            )
            Text(
                text = "Order list",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .layoutId("orderListText")
            )
        }
    }
}

@Composable
fun CartList(cart: MenuActivityData) {
    val products = cart.testData.toMutableStateList()
    val heightState by remember {
        mutableStateOf(117 * cart.lastIndexValue + 50 * cart.lastIndexValue)
    }

    Column(
        modifier = Modifier
            .padding(
                start = 25.dp,
                top = 25.dp,
                end = 25.dp
            )
            .background(background)
            .fillMaxSize(1f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = rememberLazyGridState(0),
            userScrollEnabled = false,
            modifier = Modifier
                .height(heightState.dp)
                .width(700.dp),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            content = {
                items(products) { cartItem ->
                    CartListItem(
                        image = cartItem.imageValue,
                        productName = cartItem.productNameValue,
                        productCost = cartItem.productCostValue,
                        count = products[cartItem.index].countValue,
                        index = cartItem.index,
                        onDelClick = {
                            products.removeAt(it)
                            for (i in products.indices) {
                                products[i].index = i
                            }
                        },
                        onCountChange = { it, count ->
                            products[it].countValue = count
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun CartListItem(
    image: Int,
    productName: String,
    productCost: Float,
    count: Int,
    index: Int,
    onDelClick: (Int) -> Unit,
    onCountChange: (Int, Int) -> Unit
) {
    var countState by remember {
        mutableStateOf(count)
    }
    var costState by remember {
        mutableStateOf(0f)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(113.dp)
            .background(cartItemBackground),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .background(cartItemBackground)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(113.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    start = 19.dp,
                    top = 11.dp
                )
                .size(
                    width = 148.dp,
                    height = 113.dp
                )
        ) {
            Row {
                Text(
                    text = productName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = fontPrimary
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(
                        top = 35.dp,
                        bottom = 9.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    IconButton(
                        onClick = {
                            if (countState > 0) {
                                countState -= 1
                                costState -= productCost
                            }
                            onCountChange(index, countState)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color.White)
                            .size(30.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.red_minus),
                            contentDescription = "$productName deleting",
                            modifier = Modifier
                                .size(
                                    height = 10.dp,
                                    width = 10.dp
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(
                        start = 18.75.dp,
                        end = 18.75.dp,
                        top = 3.75.dp,
                        bottom = 3.75.dp
                    )
                ) {
                    Text(
                        text = countState.toString(),
                        fontSize = 12.39.sp,
                        color = fontPrimary,
                        lineHeight = 23.sp,
                        modifier = Modifier
                            .padding(
                                top = 3.75.dp,
                                bottom = 3.75.dp
                            )
                    )
                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
                            countState += 1
                            costState = productCost * countState
                            onCountChange(index, countState)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(61.97.dp))
                            .background(Color.White)
                            .size(30.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.green_plus),
                            contentDescription = "$productName adding",
                            modifier = Modifier
                                .size(
                                    height = 10.dp,
                                    width = 10.dp
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                IconButton(
                    onClick = {
                        onDelClick(index)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Delete $productName",
                        modifier = Modifier
                            .size(24.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 42.dp)
            ) {
                Text(
                    text = "$${costState}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = fontSecondary,
                    lineHeight = 21.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartPreview() {
    CartContent(name = "CartActivity")
}

@Preview(showBackground = true)
@Composable
fun CartListItemPreview() {
    CartListItem(
        image = R.drawable.illustration,
        productName = "Spaghetti",
        productCost = 12.05f,
        count = 0,
        index = 0,
        onDelClick = {},
        onCountChange = {_, _ ->}
    )
}