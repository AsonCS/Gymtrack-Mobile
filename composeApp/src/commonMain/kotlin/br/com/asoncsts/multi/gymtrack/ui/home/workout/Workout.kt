package br.com.asoncsts.multi.gymtrack.ui.home.workout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.WorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.workoutScreenProps
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WorkoutScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.viewModel
        .state
        .collectAsState()

    WorkoutScreen(
        workoutScreenProps(
            args.navigateToExerciseExecution,
            args.workout
        ),
        state,
        modifier
    )

    LaunchedEffect(Unit) {
        args.viewModel.launch {
            getWorkout(args.workout)
        }
    }
}

abstract class WorkoutViewModel : ViewModel() {
    internal abstract val state: StateFlow<WorkoutState>

    internal abstract suspend fun getWorkout(
        workout: Workout
    )
}
