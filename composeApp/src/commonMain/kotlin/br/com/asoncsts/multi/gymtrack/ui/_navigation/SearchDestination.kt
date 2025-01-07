package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui._navigation.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.search.SearchScreen

data object SearchDestination : AppNavDestination<Args>(
    true,
    "search"
) {
    class Args(
        val navigateToExerciseDetail: (
            alias: String
        ) -> Unit,
        val viewModelApp: AppViewModel
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            SearchScreen(args)
        }
    }
}
