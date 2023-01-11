package com.example.testapplication

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapplication.reservation.ReservationCard
import com.example.testapplication.states.ReservationViewState
import com.example.testapplication.ui.theme.TestApplicationTheme
import com.example.testapplication.ui.theme.background

class ReservationActivity : ComponentActivity() {
    private lateinit var sPref: SharedPreferences
    private val data = ReservationViewState()

    companion object Keys {
        const val DATE = "data"
        const val TIME = "time"
        const val PARTY_SIZE = "party_size"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = background,
                ) {
                    ReservationCard(
                        name = this.localClassName,
                        reservationViewState = data
                    )
                }
            }
        }

        loadInputData()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveInputData()
    }

    private fun saveInputData() {
        sPref = getPreferences(MODE_PRIVATE)
        val editor = sPref.edit()

        with(editor) {
            putString(DATE, data.date)
            putString(TIME, data.time)
            putString(PARTY_SIZE, data.partySize)
        }
        editor.apply()
    }

    private fun loadInputData() {
        sPref = getPreferences(MODE_PRIVATE)

        with(sPref) {
            data.date = getString(DATE, "").toString()
            data.time = getString(TIME, "").toString()
            data.partySize = getString(PARTY_SIZE, "").toString()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    TestApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = background,
        ) {
            ReservationCard(name = "ReservationActivity", ReservationViewState())
        }
    }
}