package com.example.testapplication.menu

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.testapplication.R
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.fontSecondary
import com.example.testapplication.ui.theme.navItemColor

private fun productViewConstraints(): ConstraintSet {
    return ConstraintSet {
        val productImage = createRefFor("productImage")
        val productNameText = createRefFor("productNameText")
        val starsRow = createRefFor("starsRow")
        val productDescriptionText = createRefFor("productDescriptionText")
        val productCostText = createRefFor("productCostText")
        val addingFAB = createRefFor("addingFAB")

        constrain(productImage) {
            top.linkTo(anchor = parent.top, margin = 13.dp)
            start.linkTo(anchor = parent.start, margin = 21.dp)
            end.linkTo(anchor = parent.end, margin = 21.dp)
            bottom.linkTo(anchor = productNameText.top)
        }
        constrain(productNameText) {
            top.linkTo(anchor = productImage.bottom, margin = 10.dp)
            start.linkTo(anchor = productImage.start)
            end.linkTo(anchor = productImage.end)
            bottom.linkTo(anchor = starsRow.top)
        }
        constrain(starsRow) {
            top.linkTo(anchor = productNameText.bottom, margin = 5.dp)
            start.linkTo(anchor = productImage.start)
            end.linkTo(anchor = productImage.end)
            bottom.linkTo(anchor = productDescriptionText.top)
        }
        constrain(productDescriptionText) {
            top.linkTo(anchor = starsRow.bottom, margin = 12.dp)
            start.linkTo(anchor = productImage.start)
            end.linkTo(anchor = productImage.end)
            bottom.linkTo(anchor = productCostText.top)
        }
        constrain(productCostText) {
            top.linkTo(anchor = productDescriptionText.bottom, margin = 14.5.dp)
            start.linkTo(anchor = parent.start, margin = 15.dp)
            bottom.linkTo(anchor = parent.bottom, margin = 15.dp)
        }
        constrain(addingFAB) {
            top.linkTo(anchor = productDescriptionText.bottom, margin = 9.5.dp)
            end.linkTo(anchor = parent.end, margin = 15.dp)
            bottom.linkTo(anchor = parent.bottom, margin = 15.dp)
        }
    }
}

@Composable
fun ProductView(
    @DrawableRes image: Int,
    productName: String,
    productCost: Float,
    starsCount: Int,
    isSelected: Boolean,
    count: Int,
    index: Int,
    onClick: (Int, Int) -> Unit,
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

    var expanded by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor)
            .clickable(onClick = {
                onClick(index, count)
            })
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onClick(index, count)
                    },
                    onDoubleTap = {
                        onClick(index, count + 1)
                    },
                    onLongPress = {
                        onClick(index, 0)
                    }
                )
            }
    ) {
        val constraints = productViewConstraints()

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .fillMaxSize(fraction = 1f)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(113.dp)
                    .layoutId("productImage")
                    .padding(top = 13.dp)
            )
            Text(
                text = productName,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier
                    .layoutId("productNameText")
            )
            StarsRow(starsCount = starsCount)
            Text(
                text = stringResource(id = R.string.lorem_ipsum)
                        + stringResource(id = R.string.lorem_ipsum)
                        + stringResource(id = R.string.lorem_ipsum),
                maxLines = if (expanded) 10 else 2,
                overflow = TextOverflow.Ellipsis,
                color = textColor,
                fontSize = 12.sp,
                modifier = Modifier
                    .width(width = 130.dp)
                    .height(height = if (expanded) 90.dp else 30.dp)
                    .layoutId("productDescriptionText")
                    .clickable {
                        expanded = !expanded
                    }
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            )
            Text(
                text = "$$productCost",
                maxLines = 1,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .layoutId("productCostText")
            )
            FloatingActionButton(
                onClick = {
                    onClick(index, count + 1)
                },
                backgroundColor = noColor,
                modifier = Modifier
                    .size(33.dp)
                    .layoutId("addingFAB")
            ) {
                FABAddContent(state = count)
            }
        }
    }
}

@Composable
fun StarsRow(starsCount: Int) {
    Row(
        modifier = Modifier
            .layoutId("starsRow")
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
}

@Composable
fun FABAddContent(state: Int) {
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

@Preview
@Composable
fun ProductViewPreview() {
    ProductView(
        image = R.drawable.illustration,
        productName = "Spaghetti",
        productCost = 12.05f,
        starsCount = 4,
        isSelected = true,
        count = 0,
        index = 0
    ) {_, _ ->}
}