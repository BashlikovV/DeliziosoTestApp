package com.example.testapplication

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testapplication.reservation.ReservationCard
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReservationInstrumentedTest {

    companion object {
        @JvmStatic val RESERVATION_ACTIVITY = "ReservationActivity"
        @JvmStatic val DATE_TEXT_FIELD = "Date"
        @JvmStatic val TIME_TEXT_FIELD = "Time"
        @JvmStatic val PARTY_SIZE_TEXT_FIELD = "Party size"
        @JvmStatic val TEST_INPUT = "0123456789"
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.testapplication", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onInputDataTest() {
        composeTestRule.setContent {
            ReservationCard(name = RESERVATION_ACTIVITY)
        }

        composeTestRule.onNodeWithText(DATE_TEXT_FIELD).performTextInput(TEST_INPUT)
        composeTestRule.onNodeWithText(DATE_TEXT_FIELD).assertTextContains(TEST_INPUT)
    }

    @Test
    fun onInputTimeTest() {
        composeTestRule.setContent {
            ReservationCard(name = RESERVATION_ACTIVITY)
        }

        composeTestRule.onNodeWithText(TIME_TEXT_FIELD).performTextInput(TEST_INPUT)
        composeTestRule.onNodeWithText(TIME_TEXT_FIELD).assertTextContains(TEST_INPUT)
    }

    @Test
    fun onInputPartySizeTest() {
        composeTestRule.setContent {
            ReservationCard(name = RESERVATION_ACTIVITY)
        }

        composeTestRule.onNodeWithText(PARTY_SIZE_TEXT_FIELD).performTextInput(TEST_INPUT)
        composeTestRule.onNodeWithText(PARTY_SIZE_TEXT_FIELD).assertTextContains(TEST_INPUT)
    }
}