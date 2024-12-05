@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import br.com.asoncsts.multi.gymtrack.ui.search.searchStateValuesProvider
import org.junit.Test

class SearchScreenTest {

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

            onNodeWithText("Search Screen")
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

            onNodeWithText("Search Screen")
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

            onNodeWithText("Search Screen")
                .assertIsDisplayed()

            onNodeWithText("Remada maquina 30", substring = true)
                .assertIsDisplayed()
            onNodeWithText("Remada Alta", substring = true)
                .assertIsDisplayed()
            onNodeWithText("Biceps Halter", substring = true)
                .assertIsDisplayed()
        }
    }

}
