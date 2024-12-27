package br.com.asoncsts.multi.gymtrack.ui.home.workout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.BackHandlerContainer
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.WorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.workoutScreenProps
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WorkoutScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val workout = args.workout()
        ?: throw IllegalStateException("Workout is required")

    val state by args.viewModel
        .state
        .collectAsState()

    BackHandlerContainer(
        args.navigateUp,
        modifier
    ) {
        WorkoutScreen(
            workoutScreenProps(
                args.navigateToExerciseExecution,
                args.navigateToNewExerciseExecution,
                workout
            ),
            state,
            modifier
        )
    }

    LaunchedEffect(Unit) {
        args.viewModel.launch {
            getWorkout(workout)
        }
    }
}

abstract class WorkoutViewModel : ViewModel() {
    internal abstract val state: StateFlow<WorkoutState>

    internal abstract suspend fun getWorkout(
        workout: Workout
    )
}
