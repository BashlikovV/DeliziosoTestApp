package com.example.testapplication.view.model

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.R
import kotlinx.parcelize.Parcelize

class MenuActivityViewModel: ViewModel() {
    //val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    val testData = listOf(
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Spaghetti",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 0
        ),
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Linguine",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 1
        ),
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Capellini",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 2
        ),
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Fetuccine",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 3
        ),
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Bucatini",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 4
        ),
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            productNameValue = "Fuisilli",
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = 5
        )
    )

//    fun initState(state: State) {
//        stateLiveData.value = state
//    }
//
//    private fun getImage(id: Int): @Composable () -> Unit {
//        return { Image(
//            painter = painterResource(id = id),
//            contentDescription = "",
//            contentScale = ContentScale.Crop
//            )
//        }
//    }

    @Parcelize
    data class State(
        var imageValue: @Composable () -> Unit,
        var productNameValue: String,
        var productCostValue: Float,
        var starsCountValue: Int,
        var isSelectedValue: Boolean,
        var countValue: Int,
        val index: Int
    ): Parcelable
}