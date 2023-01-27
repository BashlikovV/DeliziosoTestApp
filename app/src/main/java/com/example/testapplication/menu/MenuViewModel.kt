package com.example.testapplication.menu

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {
    private val _count: MutableLiveData<List<Int>> = MutableLiveData(listOf(0))
    val count: LiveData<List<Int>> = _count

    fun setCount(newCount: List<Int>) {
        _count.postValue(newCount)
    }

    private val _image: MutableLiveData<List<@Composable () -> Unit>> = MutableLiveData(listOf {})
    val image: LiveData<List<@Composable () -> Unit>> = _image

    fun setImage(newImages: List<@Composable () -> Unit>) {
        _image.postValue(newImages)
    }

    private val _name: MutableLiveData<List<String>> = MutableLiveData(listOf(""))
    val name: LiveData<List<String>> = _name

    fun setName(newName: List<String>) {
        _name.postValue(newName)
    }
}