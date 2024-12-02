package br.com.asoncsts.multi.gymtrack

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import org.junit.Test

class GenericTest : ComposableTest() {

    @Test
    fun compose_ShowText_MustBeVisible() {
        setContent {
            Text(
                "Test compose"
            )
        }

        pauseTest()

        test
            .onNodeWithText("Test compose")
            .assertIsDisplayed()
    }

}
