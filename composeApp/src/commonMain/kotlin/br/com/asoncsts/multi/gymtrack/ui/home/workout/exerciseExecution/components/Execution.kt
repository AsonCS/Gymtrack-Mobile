package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

internal data class ExecutionProps(
    val execution: Execution
)

@Composable
internal fun executionProps(
    execution: Execution
) = ExecutionProps(
    execution
)

here
@Composable
internal fun Execution(
    props: ExecutionProps,
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
            props.execution.notes,
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground
                .copy(.7f),
            style = typography().bodyMedium
        )

        Text(
            props.execution.reps.toString(),
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            style = typography().headlineLarge
        )

        Text(
            props.execution.weight.toString(),
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            style = typography().headlineLarge
        )
    }
}
