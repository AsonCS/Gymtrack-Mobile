package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.search.SearchState
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.search_title
import org.jetbrains.compose.resources.stringResource

data class SearchScreenProps(
    val onExerciseClick: (
        alias: String
    ) -> Unit,
    val title: String
)

@Composable
internal fun searchScreenProps(
    onExerciseClick: (
        alias: String
    ) -> Unit
) = SearchScreenProps(
    onExerciseClick = onExerciseClick,
    title = stringResource(Res.string.search_title)
)

@Composable
internal fun SearchScreen(
    modifier: Modifier,
    props: SearchScreenProps,
    state: SearchState
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Text(
            props.title,
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().headlineLarge,
            textAlign = TextAlign.Start
        )

        when (state) {
            is SearchState.Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            SearchState.Loading -> {
                Loading()
            }

            is SearchState.Success -> {
                LazyColumn(
                    Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement
                        .spacedBy(
                            8.dp
                        )
                ) {
                    items(
                        items = state.exercises,
                        key = { it.alias }
                    ) { exercise ->
                        Exercise(
                            exercise,
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    props.onExerciseClick(
                                        exercise.alias
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}
