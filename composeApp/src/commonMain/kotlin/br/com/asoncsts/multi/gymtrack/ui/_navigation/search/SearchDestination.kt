package br.com.asoncsts.multi.gymtrack.ui._navigation.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.search.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.search.SearchScreen
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel

data object SearchDestination : SearchNavDestination<Args>(
    "search_screen"
) {
    class Args(
        val searchViewModel: SearchViewModel,
        val navigateToExerciseDetail: (
            alias: String
        ) -> Unit
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