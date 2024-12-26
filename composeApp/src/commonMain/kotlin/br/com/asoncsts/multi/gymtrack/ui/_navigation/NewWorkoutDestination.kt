package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.NewWorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutScreen

data object NewWorkoutDestination : AppNavDestination<Args>(
    false,
    "new_workout"
) {
    class Args

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            NewWorkoutScreen()
        }
    }
}
