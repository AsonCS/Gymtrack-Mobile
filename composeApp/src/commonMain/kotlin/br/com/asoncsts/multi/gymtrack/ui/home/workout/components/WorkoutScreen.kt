package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

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
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState.*

internal data class WorkoutScreenProps(
    val navigateToExerciseExecution: (
        id: String
    ) -> Unit,
    val workout: Workout
)

@Composable
internal fun workoutScreenProps(
    navigateToExerciseExecution: (
        id: String
    ) -> Unit,
    workout: Workout
) = WorkoutScreenProps(
    navigateToExerciseExecution,
    workout
)

@Composable
internal fun WorkoutScreen(
    props: WorkoutScreenProps,
    state: WorkoutState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(16.dp)
    ) {
        Text(
            props.workout.name,
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().headlineLarge,
            textAlign = TextAlign.Start
        )

        when (state) {
            is Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    Modifier
                        .weight(1f),
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            Loading -> {
                Box(
                    Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }
            }

            is Success -> {
                LazyColumn(
                    Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement
                        .spacedBy(8.dp)
                ) {
                    items(
                        items = state.exerciseExecutions,
                        key = { it.id }
                    ) { exerciseExecution ->
                        ExerciseExecution(
                            exerciseExecutionProps(
                                exerciseExecution,
                                navigateToExerciseExecution = {
                                    props.navigateToExerciseExecution(
                                        exerciseExecution.id
                                    )
                                }
                            ),
                            Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
