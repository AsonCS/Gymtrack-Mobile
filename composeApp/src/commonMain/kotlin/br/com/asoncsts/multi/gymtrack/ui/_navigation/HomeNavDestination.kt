package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeNavDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.HomeNavHost
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutDestination
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel

data object HomeNavDestination : AppNavDestination<Args>(
    false,
    "home/{destination}"
) {
    class Args(
        val homeViewModel: HomeViewModel
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

    fun routeToWorkout() = "home/${WorkoutDestination.route}"
}
