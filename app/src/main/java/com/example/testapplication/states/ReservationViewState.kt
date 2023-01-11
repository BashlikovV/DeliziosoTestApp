package com.example.testapplication.states

class ReservationViewState(
    var date: String = "Data",
    var time: String = "Time",
    var partySize: String = "Party Size"
) {
    companion object Constants {
        const val DATA = "Date"
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
            "Date" -> this.date
            "Time" -> this.time
            "Party size" -> this.partySize
            else -> ""
        }
    }
}