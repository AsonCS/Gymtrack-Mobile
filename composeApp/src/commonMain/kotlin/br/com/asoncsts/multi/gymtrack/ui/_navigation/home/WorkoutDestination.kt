package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel

data object WorkoutDestination : HomeNavDestination<Args>(
    "workout"
) {
    class Args(
        val navigateToExerciseExecution: (
            id: String
        ) -> Unit,
        val navigateToNewExerciseExecution: () -> Unit,
        val viewModel: WorkoutViewModel,
        val workout: () -> Workout?
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            WorkoutScreen(args)
        }
    }
}
