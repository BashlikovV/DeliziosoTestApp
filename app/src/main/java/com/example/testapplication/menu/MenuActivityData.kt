package com.example.testapplication.menu

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.testapplication.R
import kotlinx.parcelize.Parcelize

class MenuActivityData {
    var lastIndexValue = 6

    @DrawableRes private val imagesIds = listOf(
        R.drawable.illustration,
        R.drawable.illustration,
        R.drawable.fletuccine,
        R.drawable.capellini,
        R.drawable.capellini,
        R.drawable.illustration
    )
    private val namesList = listOf(
        "Spaghetti",
        "Linguine",
        "Capellini",
        "Fetuccine",
        "Bucatini",
        "Fuisilli"
    )
    val testData = (0..5).map {
        State(
            imageValue = imagesIds[it],
            productNameValue = namesList[it],
            productCostValue = (10..100).random().toFloat(),
            starsCountValue = (1..5).random(),
            isSelectedValue = false,
            countValue = 0,
            index = it,
        )
    }

    @Parcelize
    data class State(
        var imageValue: Int,
        var productNameValue: String,
        var productCostValue: Float,
        var starsCountValue: Int,
        var isSelectedValue: Boolean,
        var countValue: Int,
        var index: Int
    ): Parcelable
}