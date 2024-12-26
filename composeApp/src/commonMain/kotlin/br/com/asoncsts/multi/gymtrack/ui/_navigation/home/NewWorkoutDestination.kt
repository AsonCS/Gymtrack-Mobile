package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.NewWorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutScreen

data object NewWorkoutDestination : HomeNavDestination<Args>(
    "new_workout"
) {
    class Args(
        val navigateToWorkout: (
            workout: Workout
        ) -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            NewWorkoutScreen(args)
        }
    }
}
