package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.components.HomeScreen
import br.com.asoncsts.multi.gymtrack.ui.home.components.homeScreenProps
import kotlinx.coroutines.flow.StateFlow

const val TAG_HOME = "gymtrack:homeScreen"

@Composable
fun HomeScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.viewModel
        .state
        .collectAsState()

    HomeScreen(
        homeScreenProps(
            args.navigateToWorkout
        ),
        state,
        modifier
    )

    LaunchedEffect(Unit) {
        args.viewModel.getWorkouts()
    }
}

abstract class HomeViewModel : ViewModel() {
    internal var navigationArgumentWorkout: Workout? = null

    internal abstract val state: StateFlow<HomeState>

    internal abstract fun getWorkouts(
        force: Boolean = false
    )
}
