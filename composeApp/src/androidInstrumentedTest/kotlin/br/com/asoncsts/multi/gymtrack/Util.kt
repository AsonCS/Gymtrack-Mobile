package br.com.asoncsts.multi.gymtrack

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

abstract class ComposableTest {

    @get:Rule
    val test = createAndroidComposeRule<ComponentActivity>()

    protected fun setContent(
        content: @Composable () -> Unit
    ) {
        test.setContent {
            content()
        }
    }

    protected fun pauseTest(
        time: Long = 5_000
    ) {
        test.waitUntil(time) { false }
    }

}
