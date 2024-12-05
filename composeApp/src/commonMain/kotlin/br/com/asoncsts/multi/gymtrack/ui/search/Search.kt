package br.com.asoncsts.multi.gymtrack.ui.search

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExercisesMock
import br.com.asoncsts.multi.gymtrack.ui._navigation.search.SearchDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.search.components.SearchScreen
import br.com.asoncsts.multi.gymtrack.ui.search.components.searchScreenProps
import kotlinx.coroutines.flow.StateFlow

const val TAG_SEARCH = "gymtrack:search"

@Composable
fun SearchScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.searchViewModel
        .state
        .collectAsState()

    SearchScreen(
        modifier,
        searchScreenProps(
            onExerciseClick = args.navigateToExerciseDetail
        ),
        state
    )

    LaunchedEffect(Unit) {
        args.searchViewModel.getExercises()
    }
}

abstract class SearchViewModel : ViewModel() {

    internal abstract val state: StateFlow<SearchState>

    internal abstract fun getExercises(
        force: Boolean = false
    )

}

internal val searchStateValuesProvider = sequenceOf(
    SearchState.Loading,
    SearchState.Error(
        Throwable("Test error")
    ),
    SearchState.Success(
        ExercisesMock.exercises
    )
)
