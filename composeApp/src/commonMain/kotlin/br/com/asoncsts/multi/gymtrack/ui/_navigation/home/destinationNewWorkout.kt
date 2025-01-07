package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutScreen

const val newWorkoutRoute = "new_workout"

class NewWorkoutArgs(
    val navigateToWorkout: (
        workout: Workout
    ) -> Unit,
    val navigateUp: () -> Unit
)

fun NavGraphBuilder.newWorkout(
    args: NewWorkoutArgs
) {
    composable(newWorkoutRoute) {
        NewWorkoutScreen(args)
    }
}

fun NavOptionsBuilder.popUpToNewWorkout() {
    popUpTo(newWorkoutRoute) {
        inclusive = true
    }
}
