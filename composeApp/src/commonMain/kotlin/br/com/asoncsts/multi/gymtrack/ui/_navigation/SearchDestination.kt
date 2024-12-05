package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._navigation.search.SearchNavHost
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel

data object SearchDestination : AppNavDestination<Args>(
    true,
    "search"
) {
    class Args(
        val navigateToExerciseDetail: (
            alias: String
        ) -> Unit,
        val searchViewModel: SearchViewModel
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            SearchNavHost(args)
        }
    }
}
