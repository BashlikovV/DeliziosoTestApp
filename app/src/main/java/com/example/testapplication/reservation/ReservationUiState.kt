package com.example.testapplication.reservation

/**
 * [ReservationUiState] presents [GetText] composables data in [ReservationViewModel]
 * */
data class ReservationUiState(
    val currentDataState: String = "",
    val currentTimeState: String = "",
    val currentPartySizeState: String = "",
    val isError: Boolean = false
)