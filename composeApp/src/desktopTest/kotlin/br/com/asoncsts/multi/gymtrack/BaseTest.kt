@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import br.com.asoncsts.multi.gymtrack.di.koinApplication
import org.junit.Before
import org.koin.compose.KoinApplication
import org.koin.dsl.module
import java.util.Locale

interface BaseTest {

    @Before
    fun setLocale() {
        Locale.setDefault(Locale.US)
    }

    fun runTest(
        content: @Composable () -> Unit,
        block: ComposeUiTest.() -> Unit
    ) {
        runComposeUiTest {
            setContent {
                KoinApplication({
                    koinApplication(
                        module {}
                    )
                }) {
                    content()
                }
            }
            block()
        }
    }

    fun ComposeUiTest.print() {
        println(onRoot().printToString())
    }

}
