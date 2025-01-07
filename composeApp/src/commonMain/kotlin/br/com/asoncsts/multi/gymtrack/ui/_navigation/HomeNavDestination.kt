package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeNavDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.*
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel

data object HomeNavDestination : AppNavDestination<Args>(
    false,
    "home/{destination}"
) {
    class Args(
        val getExercise: (alias: String) -> Exercise,
        val navigateUp: () -> Unit,
        val viewModel: HomeViewModel
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            val destination = it.arguments
                ?.getString("destination")
                ?: throw IllegalStateException("Destination is required")
            HomeNavHost(args, destination)
        }
    }

    fun routeToNewWorkout() = "home/${NewWorkoutDestination.route}"

    fun routeToWorkout() = "home/${WorkoutDestination.route}"
}
