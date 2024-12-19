package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.HomeScreen
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel

data object HomeDestination : AppNavDestination<Args>(
    true,
    "home_screen"
) {
    class Args(
        val navigateToNewWorkout: () -> Unit,
        val navigateToWorkout: (
            workout: Workout
        ) -> Unit,
        val viewModel: HomeViewModel
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeScreen(args)
        }
    }
}