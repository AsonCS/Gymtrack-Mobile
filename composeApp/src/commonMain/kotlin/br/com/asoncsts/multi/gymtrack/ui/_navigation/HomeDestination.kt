package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.HomeNavHost

data object HomeDestination : AppNavDestination<Args>(
    true,
    "home"
) {
    class Args(
        val navigateToExerciseDetail: (
            alias: String
        ) -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeNavHost(args)
        }
    }
}
