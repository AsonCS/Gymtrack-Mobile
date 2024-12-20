@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack

import androidx.compose.ui.test.*
import org.junit.Before
import java.util.Locale

interface BaseTest {

    @Before
    fun setLocale() {
        Locale.setDefault(Locale.US)
    }

    fun ComposeUiTest.print() {
        println(onRoot().printToString())
    }

}