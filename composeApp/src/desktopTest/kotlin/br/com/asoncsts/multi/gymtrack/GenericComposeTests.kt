@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack

import androidx.compose.material3.Text
import androidx.compose.ui.test.*
import org.junit.Test

class GenericComposeTest {

    @Test
    fun compose_ShowText_MustBeVisible() {
        runComposeUiTest {
            setContent {
                Text("Test compose")
            }

            onNodeWithText("Test compose")
                .assertIsDisplayed()
        }
    }

}
