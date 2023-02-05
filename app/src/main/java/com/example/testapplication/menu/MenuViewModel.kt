package com.example.testapplication.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * [MenuViewModel] contains data for [MenuFirstScreen]
 * that contains LazyHorizontalGrid with [ProductView]
 * */

class MenuViewModel: ViewModel() {
    private val _menuUiState = MutableStateFlow(listOf(MenuUiState()))
    val menuUiState: StateFlow<List<MenuUiState>> = _menuUiState.asStateFlow()

    var selectedItem by mutableStateOf(0)
        private set

    init {
        initMenuUiState()
    }

    private fun initMenuUiState() {
        _menuUiState.update {
            (0..5).map {
                MenuUiState(
                    currentImageValue = MenuActivityData.imagesIds[it],
                    currentNameValue = MenuActivityData.namesList[it],
                    currentCostValue = (10..100).random().toFloat(),
                    currentStarsValue = (1..5).random(),
                    currentIsSelectedValue = false,
                    currentCountValue = 0,
                    index = it
                )
            }
        }
    }

    fun onSelectItem(index: Int) {
        selectedItem = index
    }

    fun onCountChanged(index: Int, newValue: Int) {
        _menuUiState.update {
            updateCountItemValue(index, newValue)
        }
    }

    private fun updateCountItemValue(index: Int, newValue: Int): List<MenuUiState> {
        return _menuUiState.value.map {
            it.copy(
                currentCountValue = if (it.index == index) newValue else it.currentCountValue
            )
        }
    }
}