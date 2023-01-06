package com.example.testapplication.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar

@Composable
fun CartContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .fillMaxSize()
    ) {
        TopNavBar(name = name)
        Footer(name = name)
    }
}