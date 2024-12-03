@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import br.com.asoncsts.multi.gymtrack.ui.home.homeStateValuesProvider
import org.junit.Test

class HomeScreenTest {

    @Test
    fun homeScreen_HomeState0() {
        runComposeUiTest {
            setContent {
                HomeScreen(
                    Modifier,
                    homeScreenProps { },
                    homeStateValuesProvider
                        .elementAt(0)
                )
            }

            onNodeWithText("Home Screen")
                .assertIsDisplayed()

            onNodeWithContentDescription("Loading component")
                .assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_HomeState1() {
        runComposeUiTest {
            setContent {
                HomeScreen(
                    Modifier,
                    homeScreenProps { },
                    homeStateValuesProvider
                        .elementAt(1)
                )
            }

            onNodeWithText("Home Screen")
                .assertIsDisplayed()
            onNodeWithText("Test error")
                .assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_HomeState2() {
        runComposeUiTest {
            setContent {
                HomeScreen(
                    Modifier,
                    homeScreenProps { },
                    homeStateValuesProvider
                        .elementAt(2)
                )
            }

            onNodeWithText("Home Screen")
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
