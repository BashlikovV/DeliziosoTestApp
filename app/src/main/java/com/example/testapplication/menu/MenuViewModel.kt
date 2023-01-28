package com.example.testapplication.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class MenuUiState(
    val counts: List<Int> = listOf(0),
    val images: List<Int>? = null,
    val names: List<String>? = null
)
class MenuUiViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    fun initUiState(counts: List<Int>, images: List<Int>?, names: List<String>?) {
        _uiState.update { currentState ->
            currentState.copy(
                counts = counts,
                images = images,
                names = names
            )
        }
    }
}