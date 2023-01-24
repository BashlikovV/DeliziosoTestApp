package com.example.testapplication.view.model

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.testapplication.R
import kotlinx.parcelize.Parcelize

class MenuActivityViewModel: ViewModel() {
//    val state: LiveData<State> get() = stateLiveData
//    private val stateLiveData = MutableLiveData<State>()
    companion object {

    }
    var lastIndexValue = 6

    private val imagesIds = listOf(
        R.drawable.illustration,
        R.drawable.illustration,
        R.drawable.fletuccine,
        R.drawable.capellini,
        R.drawable.capellini,
        R.drawable.illustration
    )
    val namesList = listOf(
        "Spaghetti",
        "Linguine",
        "Capellini",
        "Fetuccine",
        "Bucatini",
        "Fuisilli"
    )
    val testData = (0..5).map {
        State(
            imageValue = {
                Image(
                    painter = painterResource(id = imagesIds[it]),
                    contentDescription = namesList[it],
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(113.dp)
                )
            },
            productNameValue = namesList[it],
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = it,
        )
    }

//    fun initState(state: State) {
//        stateLiveData.value = state
//    }

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
        var index: Int
    ): Parcelable
}