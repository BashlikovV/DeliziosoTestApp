package com.example.testapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapplication.ui.theme.TestApplicationTheme
import com.example.testapplication.reservation.ReservationCard
import com.example.testapplication.ui.theme.background

class ReservationActivity : ComponentActivity() {
    private lateinit var sPref: SharedPreferences

    companion object Keys {
        const val DATE = "data"
        const val TIME = "time"
        const val PARTY_SIZE = "party_size"
    }

    object Data {
        var date = ""
        var time = ""
        var partySize = ""
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
                    ReservationCard(data = this.localClassName)
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
            putString(DATE, Data.date)
            putString(TIME, Data.time)
            putString(PARTY_SIZE, Data.partySize)
        }
        editor.apply()

        Log.i("MYTAGS", Data.date)
        Log.i("MYTAGS", Data.time)
        Log.i("MYTAGS", Data.partySize)
    }

    private fun loadInputData() {
        sPref = getPreferences(MODE_PRIVATE)

        with(sPref) {
            Data.date = getString(DATE, "").toString()
            Data.time = getString(TIME, "").toString()
            Data.partySize = getString(PARTY_SIZE, "").toString()
        }

        Log.i("MYTAGL", Data.date)
        Log.i("MYTAGL", Data.time)
        Log.i("MYTAGL", Data.partySize)
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
            ReservationCard(data = "ReservationActivity")
        }
    }
}