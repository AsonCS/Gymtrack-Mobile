package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack._mock.data.user.workout.WorkoutMock
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._components.ScreenTopBar
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

internal data class HomeScreenProps(
    val labelNew: String,
    val labelTitle: String,
    val navigateToNewWorkout: () -> Unit,
    val navigateToWorkout: (
        workout: Workout
    ) -> Unit
)

@Composable
internal fun homeScreenProps(
    navigateToNewWorkout: () -> Unit,
    navigateToWorkout: (
        workout: Workout
    ) -> Unit,
    labelNew: String = stringResource(Res.string.home_label_new),
    labelTitle: String = stringResource(Res.string.home_label_title)
) = HomeScreenProps(
    labelNew,
    labelTitle,
    navigateToNewWorkout,
    navigateToWorkout
)

@Composable
internal fun HomeScreen(
    props: HomeScreenProps,
    state: HomeState,
    modifier: Modifier = Modifier
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
        ScreenTopBar(
            onNavigateUp = null,
            title = props.labelTitle
        ) {
            Text(
                props.labelNew,
                Modifier
                    .clickable {
                        props.navigateToNewWorkout()
                    },
                color = colors().secondary,
                style = typography().headlineSmall
            )
        }

        when (state) {
            is HomeState.Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    Modifier
                        .weight(1f),
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            HomeState.Loading -> {
                Box(
                    Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }
            }

            is HomeState.Success -> Success(
                Modifier
                    .weight(1f),
                props.navigateToWorkout,
                state
            )
        }
    }
}

@Composable
private fun Success(
    modifier: Modifier,
    navigateToWorkout: (
        workout: Workout
    ) -> Unit,
    state: HomeState.Success
) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement
            .spacedBy(8.dp)
    ) {
        items(
            items = state.workouts,
            key = { it.id }
        ) { workout ->
            Workout(
                workoutProps(
                    navigateToWorkout = {
                        navigateToWorkout(
                            workout
                        )
                    },
                    workout = workout
                )
            )
        }
    }
}

internal val homeStateValuesProvider = sequenceOf(
    HomeState.Loading,
    HomeState.Error(
        Throwable("Test error")
    ),
    HomeState.Success(
        WorkoutMock.workouts
    )
)
