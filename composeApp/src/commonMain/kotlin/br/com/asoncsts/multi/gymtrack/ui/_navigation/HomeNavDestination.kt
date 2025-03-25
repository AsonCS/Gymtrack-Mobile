package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.*
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel

const val homeNavArgDestination = "destination"
const val homeNavPathHome = "home/"
const val homeNavRoute = "$homeNavPathHome{$homeNavArgDestination}"

class HomeNavArgs(
    val exercisesSource: ExercisesSource,
    val navigateUp: () -> Unit,
    val viewModel: HomeViewModel
)

fun NavGraphBuilder.homeNav(
    args: HomeNavArgs
) {
    composable(homeNavRoute) {
        val destination = it.arguments
            ?.getString(homeNavArgDestination)
            ?: throw IllegalStateException("Destination is required")
        HomeNavHost(args, destination)
    }
}

fun NavHostController.navigateToWorkout() {
    navigate(
        "$homeNavPathHome$workoutRoute"
    )
}
