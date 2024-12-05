package br.com.asoncsts.multi.gymtrack.ui._navigation.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

sealed class SearchNavDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun SearchNavHost(
    args: Args,
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route,
        modifier = modifier
    ) {
        SearchDestination(
            SearchDestination.Args(
                searchViewModel,
                navigateToExerciseDetail = args.navigateToExerciseDetail
            ),
            this
        )
    }
}
