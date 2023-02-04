package com.example.testapplication

import com.example.testapplication.reservation.ReservationUiState
import com.example.testapplication.reservation.ReservationViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ReservationViewModelTest {
    private val viewModel = ReservationViewModel()

    companion object {
        @JvmStatic val DATE_FIELD = 0
        @JvmStatic val TIME_FIELD = 1
        @JvmStatic val PARTY_SIZE_FIELD = 2
        @JvmStatic val TEST_CORRECT_VALUE = "1234567"
        @JvmStatic val TEST_INCORRECT_VALUE = "1234"
    }

    @Test
    fun reservationViewModel_correctInput_DateField() {
        viewModel.onReservationDataChanged(
            index = DATE_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        val currentReservationState: ReservationUiState = viewModel.reservationUiState.value

        assertEquals(TEST_CORRECT_VALUE, currentReservationState.currentDataState)
    }

    @Test
    fun reservationViewModel_CorrectInput_TimeField() {
        viewModel.onReservationDataChanged(
            index = TIME_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        val currentReservationState: ReservationUiState = viewModel.reservationUiState.value

        assertEquals(TEST_CORRECT_VALUE, currentReservationState.currentTimeState)
    }

    @Test
    fun reservationViewModel_CorrectInput_PartySizeField() {
        viewModel.onReservationDataChanged(
            index = PARTY_SIZE_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        val currentReservationState: ReservationUiState = viewModel.reservationUiState.value

        assertEquals(TEST_CORRECT_VALUE, currentReservationState.currentPartySizeState)
    }

    @Test
    fun reservationViewModel_IncorrectInput_DateField() {
        viewModel.onReservationDataChanged(
            index = DATE_FIELD,
            newTextValue = TEST_INCORRECT_VALUE
        )
        viewModel.onReservationDataChanged(
            index = TIME_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        viewModel.onReservationDataChanged(
            index = PARTY_SIZE_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        viewModel.onActionDone()
        val currentReservationUiState: ReservationUiState = viewModel.reservationUiState.value

        assertTrue(currentReservationUiState.isError)
    }

    @Test
    fun reservationViewModel_CorrectInput_AllFields() {
        viewModel.onReservationDataChanged(
            index = DATE_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        viewModel.onReservationDataChanged(
            index = TIME_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        viewModel.onReservationDataChanged(
            index = PARTY_SIZE_FIELD,
            newTextValue = TEST_CORRECT_VALUE
        )
        viewModel.onActionDone()
        val currentReservationUiState: ReservationUiState = viewModel.reservationUiState.value

        assertFalse(currentReservationUiState.isError)
    }
}