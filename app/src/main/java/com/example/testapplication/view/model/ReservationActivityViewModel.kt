package com.example.testapplication.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservationActivityViewModel: ViewModel() {

    private val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    private fun initState(state: State) {
        stateLiveData.value = state
    }

    fun saveReservationActivityData(index: Int, str: String) {
        when(index) {
            0 -> stateLiveData.value?.dataValue = str
            1 -> stateLiveData.value?.timeValue = str
            2 -> stateLiveData.value?.partySizeValue = str
        }
    }

    fun loadReservationViewModel(value: String): String {
        return when(value) {
            DATA -> state.value?.dataValue ?: ""
            TIME -> state.value?.timeValue ?: ""
            PARTY_SIZE -> state.value?.partySizeValue ?: ""
            else -> ""
        }
    }

    fun makeViewModel(viewModel: ReservationActivityViewModel) {
        if (viewModel.state.value == null) {
            viewModel.initState(State(
                dataValue = "",
                timeValue = "",
                partySizeValue = ""
            ))
        }
    }

    data class State(
        var dataValue: String,
        var timeValue: String,
        var partySizeValue: String
    )

    companion object Constants {
        const val DATA = "Data"
        const val TIME = "Time"
        const val PARTY_SIZE = "Party size"
    }
}