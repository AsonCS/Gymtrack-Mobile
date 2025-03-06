package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.runtime.Composable
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import gymtrack.composeapp.generated.resources.*
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
    val labelCreate: String,
    val labelNewExecution: String,
    val labelNotes: String,
    val labelReps: String,
    val labelWeight: String,
    val onCreate: () -> Unit
)

@Composable
internal fun newExecutionProps(
    onCreate: () -> Unit,
    labelCreate: String = stringResource(
        Res.string.label_create
    ),
    labelNewExecution: String = stringResource(
        Res.string.execution_label_new
    ),
    labelNotes: String = stringResource(
        Res.string.execution_label_notes
    ),
    labelReps: String = stringResource(
        Res.string.execution_label_reps
    ),
    labelWeight: String = stringResource(
        Res.string.execution_label_weight
    )
) = NewExecutionProps(
    labelCreate,
    labelNewExecution,
    labelNotes,
    labelReps,
    labelWeight,
    onCreate
)
