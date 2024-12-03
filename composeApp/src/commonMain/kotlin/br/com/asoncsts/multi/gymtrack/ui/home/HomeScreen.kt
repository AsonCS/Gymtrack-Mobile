package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExercisesMock
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeScreenDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.components.Exercise

@Composable
fun HomeScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.homeViewModel
        .state
        .collectAsState()

    HomeScreen(
        modifier = modifier,
        props = HomeProps(),
        state = state
    )

    LaunchedEffect(Unit) {
        //TAG_APP.log("HomeScreen.appViewModel.stateTopBarUpdate")
        args.appViewModel.stateTopBarUpdate(
            showUser = true
        )
        args.homeViewModel.getExercises()
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    props: HomeProps,
    state: HomeState
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Text(
            "Home Screen",
            style = typography().titleSmall
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
                state.exercises.forEach {
                    Exercise(it)
                }
            }
        }
    }
}

internal val homeStateValuesProvider = sequenceOf(
    HomeState.Loading,
    HomeState.Error(
        Throwable("Test error")
    ),
    HomeState.Success(
        ExercisesMock.exercises
    )
)
