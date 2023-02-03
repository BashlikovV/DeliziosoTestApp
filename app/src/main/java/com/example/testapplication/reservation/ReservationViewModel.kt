package com.example.testapplication.reservation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * [ReservationViewModel] contains all data from [ReservationCard] input of [GetText] composables
 * */
class ReservationViewModel : ViewModel() {

    private val _reservationUiState = MutableStateFlow(ReservationUiState())

    var userDataInput by mutableStateOf("")
        private set
    var userTimeInput by mutableStateOf("")
        private set
    var userPartySizeInput by mutableStateOf("")
        private set

    fun onReservationDataChanged(index: Int, newTextValue: String) {
        _reservationUiState.update {
            updateTextValue(index, newTextValue)
        }
    }

    private fun updateUserDataInput(newValue: String) {
        userDataInput = newValue
    }

    private fun updateUserTimeInput(newValue: String) {
        userTimeInput = newValue
    }

    private fun updateUserPartySizeInput(newValue: String) {
        userPartySizeInput = newValue
    }

    private fun updateTextValue(index: Int, newTextValue: String): ReservationUiState {
        return when (index) {
            0 -> {
                updateUserDataInput(newTextValue)
                _reservationUiState.value.copy(currentDataState = newTextValue)
            }
            1 -> {
                updateUserTimeInput(newTextValue)
                _reservationUiState.value.copy(currentTimeState = newTextValue)
            }
            2 -> {
                updateUserPartySizeInput(newTextValue)
                _reservationUiState.value.copy(currentPartySizeState = newTextValue)
            }
            else -> { ReservationUiState() }
        }
    }
}