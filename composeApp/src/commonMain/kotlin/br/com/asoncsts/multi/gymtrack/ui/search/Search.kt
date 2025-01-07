package br.com.asoncsts.multi.gymtrack.ui.search

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._navigation.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.search.components.SearchScreen
import br.com.asoncsts.multi.gymtrack.ui.search.components.searchScreenProps

@Composable
fun SearchScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.viewModelApp
        .stateExercises
        .collectAsState()

    SearchScreen(
        modifier,
        searchScreenProps(
            onExerciseClick = args.navigateToExerciseDetail
        ),
        state
    )
}
