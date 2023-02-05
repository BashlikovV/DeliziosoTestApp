package com.example.testapplication.menu

import androidx.annotation.DrawableRes

/**
 * [MenuUiState] presents [MenuFirstScreen] LazyHorizontalGrid composables data in [MenuViewModel]
 * */

data class MenuUiState(
    @DrawableRes val currentImageValue: Int = 0,
    val currentNameValue: String = "",
    val currentCostValue: Float = 0f,
    val currentStarsValue: Int = 0,
    val currentIsSelectedValue: Boolean = false,
    val currentCountValue: Int = 0,
    val index: Int = 0
)