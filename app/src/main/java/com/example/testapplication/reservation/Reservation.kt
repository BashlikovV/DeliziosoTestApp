package com.example.testapplication.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapplication.R
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar
import com.example.testapplication.ui.theme.cuisineColor
import com.example.testapplication.ui.theme.inputColor
import com.example.testapplication.ui.theme.lorColor

@Composable
fun ReservationCard(
    name: String,
    reservationViewModel: ReservationViewModel = viewModel()
) {
    val reservationUiState by reservationViewModel.reservationUiState.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopNavBar(name = name)
        Row {
            Text(
                text = "Book a table",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = cuisineColor
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.component),
                contentDescription = "Component",
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(top = 30.dp, bottom = 30.dp)
                    .size(
                        width = 353.94.dp,
                        height = 256.29.dp
                    )
                    .offset(x = (-60).dp),
                contentScale = ContentScale.FillHeight,
            )
        }
        GetText(
            value = "Date",
            textState = reservationUiState.currentDataState,
            isError = reservationUiState.isError,
            onActionDone = { reservationViewModel.onActionDone() }
        ) {
            reservationViewModel.onReservationDataChanged(0, it)
        }
        GetText(
            value = "Time",
            textState = reservationUiState.currentTimeState,
            isError = reservationUiState.isError,
            onActionDone = { reservationViewModel.onActionDone() }
        ) {
            reservationViewModel.onReservationDataChanged(1, it)
        }
        GetText(
            value = "Party size",
            textState = reservationUiState.currentPartySizeState,
            isError = reservationUiState.isError,
            onActionDone = { reservationViewModel.onActionDone() }
        ) {
            reservationViewModel.onReservationDataChanged(2, it)
        }
        Footer(name = name)
    }
}

@Composable
fun GetText(
    value: String,
    textState: String,
    isError: Boolean = false,
    onActionDone: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 25.dp)
    ) {
        TextField(
            value = textState,
            onValueChange = {
                onValueChange(it)
            },
            isError = isError,
            label = {
                Text(
                    text = value,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(inputColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(lorColor),
            keyboardActions = KeyboardActions(onDone = {
                onActionDone()
            })
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    ReservationCard(name = "text", ReservationViewModel())
}