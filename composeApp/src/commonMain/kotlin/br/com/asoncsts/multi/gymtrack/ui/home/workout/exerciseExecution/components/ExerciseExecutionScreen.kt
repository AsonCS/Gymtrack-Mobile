package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._components.ScreenTopBar
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionState
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionState.*

internal data class ExerciseExecutionScreenProps(
    val navigateUp: () -> Unit
)

@Composable
internal fun exerciseExecutionScreenProps(
    navigateUp: () -> Unit
) = ExerciseExecutionScreenProps(
    navigateUp
)

here
@Composable
internal fun ExerciseExecutionScreen(
    props: ExerciseExecutionScreenProps,
    state: ExerciseExecutionState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is Error -> {
            Text(
                state.throwable.message
                    ?: "Error",
                Modifier
                    .fillMaxSize(),
                color = colors().error,
                style = typography().titleSmall
            )
        }

        Loading -> {
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Loading()
            }
        }

        is Success -> Success(
            state.exerciseExecution,
            props.navigateUp,
            modifier
        )
    }
}

@Composable
private fun Success(
    exerciseExecution: ExerciseExecution.Detail,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    val locale = Locale.current

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(16.dp)
    ) {
        val exerciseTitle = exerciseExecution.exercise
            ?.title

        if (exerciseTitle != null) {
            ScreenTopBar(
                navigateUp,
                exerciseTitle
            )

            HorizontalDivider()
        }

        ScreenTopBar(
            if (exerciseTitle != null)
                null
            else
                navigateUp,
            exerciseExecution.name
        )

        val description = exerciseExecution.description
        if (!description.isNullOrEmpty()) {
            Text(
                description.capitalize(locale),
                color = colors().onBackground
                    .copy(.7f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = typography().bodySmall
            )
        }

        LazyColumn(
            Modifier
                .weight(1f),
            verticalArrangement = Arrangement
                .spacedBy(8.dp)
        ) {
            items(
                items = exerciseExecution.executions,
                key = { it.id }
            ) { execution ->
                Execution(
                    executionProps(
                        execution
                    )
                )
            }
        }
    }
}
