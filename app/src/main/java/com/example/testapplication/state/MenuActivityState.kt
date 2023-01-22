package com.example.testapplication.state

import android.os.Parcelable
import androidx.compose.runtime.toMutableStateMap
import com.example.testapplication.view.model.MenuActivityViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuActivityState(var data: MutableMap<String, Int>) : Parcelable {
    init {
        val _data = (0..5).map {
            MenuActivityViewModel().namesList[it] to 0
        }
        data = _data.toMutableStateMap()
    }

    fun saveMenuActivityData(key: String, value: Int) {
        data[key] = value
    }

//    fun loadMenuActivityData(key: String): Int {
//        return data[key]!!
//    }
}