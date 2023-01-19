package com.example.testapplication.view.model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize

class ReservationActivityViewModel: ViewModel() {

    val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    fun initState(state: State) {
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

    @Parcelize
    data class State(
        var dataValue: String,
        var timeValue: String,
        var partySizeValue: String
    ): Parcelable

    companion object Constants {
        @JvmStatic val DATA = "Data"
        @JvmStatic val TIME = "Time"
        @JvmStatic val PARTY_SIZE = "Party size"
    }
}