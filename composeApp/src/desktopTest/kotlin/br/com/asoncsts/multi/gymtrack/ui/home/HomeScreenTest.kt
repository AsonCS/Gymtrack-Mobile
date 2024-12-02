@file:OptIn(ExperimentalTestApi::class)

package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExercisesMock
import org.junit.Test

class HomeScreenTest {

    @Test
    fun homeScreen_ShowText_MustBeVisible() {
        runComposeUiTest {
            setContent {
                HomeScreen(
                    Modifier,
                    HomeProps(),
                    HomeState.Success(
                        ExercisesMock.exercises
                    )
                )
            }

            onRoot(true).printToString()

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
