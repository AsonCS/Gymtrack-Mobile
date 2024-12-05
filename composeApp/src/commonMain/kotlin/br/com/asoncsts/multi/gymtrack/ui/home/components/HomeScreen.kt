package br.com.asoncsts.multi.gymtrack.ui.home.components

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
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.home_title
import org.jetbrains.compose.resources.stringResource

data class HomeScreenProps(
    val onExerciseClick: (
        id: String
    ) -> Unit,
    val title: String
)

@Composable
internal fun homeScreenProps(
    onExerciseClick: (
        id: String
    ) -> Unit
) = HomeScreenProps(
    onExerciseClick = onExerciseClick,
    title = stringResource(Res.string.home_title)
)

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    props: HomeScreenProps,
    state: HomeState
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
            is HomeState.Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            HomeState.Loading -> {
                Loading()
            }

            is HomeState.Success -> {
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
