package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExercisesMock
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeScreenDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.components.HomeScreen
import br.com.asoncsts.multi.gymtrack.ui.home.components.homeScreenProps
import kotlinx.coroutines.flow.StateFlow

const val TAG_HOME = "gymtrack:home"

@Composable
fun HomeScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.homeViewModel
        .state
        .collectAsState()

    HomeScreen(
        modifier,
        homeScreenProps {
            args.homeViewModel.getExercise(it)
        },
        state
    )

    LaunchedEffect(Unit) {
        args.homeViewModel.getExercises()
    }
}

abstract class HomeViewModel : ViewModel() {

    internal abstract val state: StateFlow<HomeState>

    internal abstract fun getExercise(
        id: String,
        force: Boolean = false
    )

    internal abstract fun getExercises(
        force: Boolean = false
    )

}

internal val homeStateValuesProvider = sequenceOf(
    HomeState.Loading,
    HomeState.Error(
        Throwable("Test error")
    ),
    HomeState.Success(
        ExercisesMock.exercises
    )
)
