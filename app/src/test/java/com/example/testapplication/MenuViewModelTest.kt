package com.example.testapplication

import com.example.testapplication.menu.MenuViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MenuViewModelTest {
    private val viewModel = MenuViewModel()

    companion object {
        @JvmStatic val SELECTED_TEST_VALUE = 3
        @JvmStatic val COUNT_TEST_VALUE = 10
    }

    @Test
    fun menuViewModel_OnSelectItemChange() {
        assertTrue(viewModel.selectedItem == 0)

        viewModel.onSelectItem(SELECTED_TEST_VALUE)

        assertEquals(SELECTED_TEST_VALUE, viewModel.selectedItem)
    }

    @Test
    fun menuViewModel_OnCountChange() {
        viewModel.onCountChanged(
            index = 0,
            newValue = COUNT_TEST_VALUE
        )

        val currentMenuState = viewModel.menuUiState.value
        assertEquals(COUNT_TEST_VALUE, currentMenuState[0].currentCountValue)
    }

    @Test
    fun menuViewModel_OnCountsChange() {
        viewModel.onCountChanged(
            index = 5,
            newValue = COUNT_TEST_VALUE
        )
        viewModel.onCountChanged(
            index = 3,
            newValue = COUNT_TEST_VALUE
        )

        val currentMenuState = viewModel.menuUiState.value
        assertEquals(COUNT_TEST_VALUE, currentMenuState[5].currentCountValue)
        assertEquals(COUNT_TEST_VALUE, currentMenuState[3].currentCountValue)
    }
}