package com.example.testapplication.reservation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * [ReservationViewModel] contains all data from [ReservationCard] input of [GetText] composables
 * */
class ReservationViewModel : ViewModel() {

    private val _reservationUiState = MutableStateFlow(ReservationUiState())
    val reservationUiState = _reservationUiState.asStateFlow()

    fun onReservationDataChanged(index: Int, newTextValue: String) {
        _reservationUiState.update {
            updateTextValue(index, newTextValue)
        }
    }

    private fun updateTextValue(index: Int, newTextValue: String): ReservationUiState {
        return when (index) {
            0 -> _reservationUiState.value.copy(currentDataState = newTextValue)
            1 -> _reservationUiState.value.copy(currentTimeState = newTextValue)
            2 -> _reservationUiState.value.copy(currentPartySizeState = newTextValue)
            else -> { ReservationUiState() }
        }
    }
}