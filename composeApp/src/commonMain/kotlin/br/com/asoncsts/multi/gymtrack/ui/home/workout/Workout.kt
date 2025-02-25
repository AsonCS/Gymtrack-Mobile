package br.com.asoncsts.multi.gymtrack.ui.home.workout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutArgs
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.WorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.components.workoutScreenProps
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WorkoutScreen(
    args: WorkoutArgs,
    modifier: Modifier = Modifier
) {
    val workout = args.workout()
        ?: throw IllegalStateException("Workout is required")

    val shared by args.viewModel
        .shared
        .collectAsState(null)
    val state by args.viewModel
        .state
        .collectAsState()

    WorkoutScreen(
        workoutScreenProps(
            {
                args.viewModel.launch {
                    addNewExerciseExecution(
                        it,
                        workout
                    )
                }
            },
            args.navigateUp,
            args.navigateToExerciseExecution,
            args.navigateToNewExerciseExecution,
            workout
        ),
        shared,
        state,
        modifier
    )

    LaunchedEffect(Unit) {
        args.viewModel.launch {
            getWorkout(workout)
        }
    }
}

abstract class WorkoutViewModel : ViewModel() {
    internal abstract val shared: SharedFlow<WorkoutShared>
    internal abstract val state: StateFlow<WorkoutState>

    internal abstract suspend fun addNewExerciseExecution(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    )

    internal abstract suspend fun getWorkout(
        workout: Workout
    )
}
