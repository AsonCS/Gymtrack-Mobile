package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesState

@PreviewMedPhone
@Composable
private fun Preview(
    @PreviewParameter(SearchStateValuesProvider::class) state: ExercisesState
) {
    PreviewContainer {
        SearchScreen(
            Modifier,
            searchScreenProps(),
            state
        )
    }
}

@Composable
private fun searchScreenProps() = SearchScreenProps(
    onExerciseClick = {},
    title = "stringResource(R.string.search_title)"
)

private class SearchStateValuesProvider : PreviewParameterProvider<ExercisesState> {
    override val values = searchStateValuesProvider
}
