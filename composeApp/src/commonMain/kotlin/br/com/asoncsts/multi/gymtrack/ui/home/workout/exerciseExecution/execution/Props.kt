package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.runtime.Composable
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.label_name
import org.jetbrains.compose.resources.stringResource

internal data class ExecutionProps(
    val execution: Execution
)

@Composable
internal fun executionProps(
    execution: Execution
) = ExecutionProps(
    execution
)

internal data class NewExecutionProps(
    val labelNewExecution: String,
    val labelNotes: String,
    val labelReps: String,
    val labelWeight: String,
)

@Composable
internal fun newExecutionProps(
    labelNewExecution: String = stringResource(
        Res.string.label_name
    ),
    labelNotes: String = stringResource(
        Res.string.label_name
    ),
    labelReps: String = stringResource(
        Res.string.label_name
    ),
    labelWeight: String = stringResource(
        Res.string.label_name
    )
) = NewExecutionProps(
    labelNewExecution,
    labelNotes,
    labelReps,
    labelWeight
)
