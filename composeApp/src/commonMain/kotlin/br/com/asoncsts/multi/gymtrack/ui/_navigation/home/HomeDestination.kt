package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.HomeDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.HomeScreen
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel

data object HomeDestination : HomeNavDestination<Args>(
    "home_screen"
) {
    class Args(
        val homeViewModel: HomeViewModel,
        val navigateToExerciseDetail: (
            alias: String
        ) -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeScreen(args)
        }
    }
}
