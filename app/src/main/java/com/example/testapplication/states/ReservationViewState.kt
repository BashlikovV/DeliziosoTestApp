package com.example.testapplication.states

class ReservationViewState(
    var date: String = "",
    var time: String = "",
    var partySize: String = ""
) {
    companion object Constants {
        const val DATA = "Data"
        const val TIME = "Time"
        const val PARTY_SIZE = "Party size"
    }

    fun saveInputData(index: Int, str: String) {
        when(index) {
            0 -> this.date = str
            1 -> this.time = str
            2 -> this.partySize = str
        }
    }

    fun loadReservationViewState(value: String): String {
        return when(value) {
            DATA -> this.date
            TIME -> this.time
            PARTY_SIZE -> this.partySize
            else -> ""
        }
    }
}