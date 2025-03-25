package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel

const val workoutRoute = "workout"

class WorkoutArgs(
    val navigateToExerciseExecution: (
        id: String
    ) -> Unit,
    val navigateUp: () -> Unit,
    val viewModel: WorkoutViewModel,
    val workout: () -> Workout?
)

fun NavGraphBuilder.workout(
    args: WorkoutArgs
) {
    composable(workoutRoute) {
        WorkoutScreen(args)
    }
}

fun NavHostController.navigateToWorkout() {
    navigate(workoutRoute)
}
