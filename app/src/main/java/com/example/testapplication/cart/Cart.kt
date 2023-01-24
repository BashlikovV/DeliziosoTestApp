package com.example.testapplication.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapplication.R
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
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
        Footer(name = name)
    }
}

@Composable
fun CartListItem(
    image: @Composable () -> Unit,
    productName: String,
    productCost: Float,
    isSelected: Boolean,
    count: Int,
    index: Int,
    onClick: (Int, Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(113.dp)
            .background(cartItemBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 25.dp)
        ) {
            image()
        }
        Column(
            modifier = Modifier
                .padding(
                    start = 19.dp,
                    top = 11.dp
                )
                .size(
                    width = 178.dp,
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
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.red_minus),
                            contentDescription = "$productName deleting",
                            modifier = Modifier
                                .size(30.dp),
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
                        text = count.toString(),
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
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(RoundedCornerShape(61.97.dp))
                            .background(Color.White),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.green_plus),
                            contentDescription = "$productName adding",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(bottom = 4.dp),
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
                    onClick = { /*TODO*/ }
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
                    text = (productCost * count).toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = fontSecondary,
                    lineHeight = 21.sp
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CartPreview() {
//    CartContent(name = "CartActivity")
//}

@Preview(showBackground = true)
@Composable
fun CartListItemPreview() {
    CartListItem(
        image = {
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        },
        productName = "Spaghetti",
        productCost = 12.05f,
        isSelected = true,
        count = 0,
        index = 0
    ) {_, _ ->}
}