@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import br.com.asoncsts.multi.gymtrack.BaseTest
import org.junit.Test

class SearchScreenTest : BaseTest {

    @Test
    fun searchScreen_SearchState0() {
        runComposeUiTest {
            setContent {
                SearchScreen(
                    Modifier,
                    searchScreenProps { },
                    searchStateValuesProvider
                        .elementAt(0)
                )
            }

            onNodeWithText("All exercises.", ignoreCase = true, useUnmergedTree = true)
                .assertIsDisplayed()

            onNodeWithContentDescription("Loading component")
                .assertIsDisplayed()
        }
    }

    @Test
    fun searchScreen_SearchState1() {
        runComposeUiTest {
            setContent {
                SearchScreen(
                    Modifier,
                    searchScreenProps { },
                    searchStateValuesProvider
                        .elementAt(1)
                )
            }

            onNodeWithText("All exercises.")
                .assertIsDisplayed()
            onNodeWithText("Test error")
                .assertIsDisplayed()
        }
    }

    @Test
    fun searchScreen_SearchState2() {
        runComposeUiTest {
            setContent {
                SearchScreen(
                    Modifier,
                    searchScreenProps { },
                    searchStateValuesProvider
                        .elementAt(2)
                )
            }

            onNodeWithText("All exercises.")
                .assertIsDisplayed()

            onNodeWithText("Remada Maquina 30", substring = true)
                .assertIsDisplayed()
            onNodeWithText("Remada Alta", substring = true)
                .assertIsDisplayed()
            onNodeWithText("Biceps Halter", substring = true)
                .assertIsDisplayed()
        }
    }

}
