package com.example.testapplication.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testapplication.footer.Footer
import com.example.testapplication.homepage.TopNavBar

@Composable
fun OrderContent(name: String) {
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
    ) {
        TopNavBar(name = name)
        Footer(name = name)
    }

}