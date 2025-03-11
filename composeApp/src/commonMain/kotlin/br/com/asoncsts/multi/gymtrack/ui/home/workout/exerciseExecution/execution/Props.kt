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
    val labelRemove: String,
    val labelReps: String,
    val labelUpdate: String,
    val labelWeight: String,
    val onCreate: () -> Unit,
    val onRemove: (
        executionId: String
    ) -> Unit,
    val onToggleDialog: () -> Unit
)

@Composable
internal fun newExecutionProps(
    onCreate: () -> Unit,
    onRemove: (
        executionId: String
    ) -> Unit,
    onToggleDialog: () -> Unit,
    labelCreate: String = stringResource(
        Res.string.label_create
    ),
    labelNewExecution: String = stringResource(
        Res.string.execution_label_new
    ),
    labelNotes: String = stringResource(
        Res.string.execution_label_notes
    ),
    labelRemove: String = stringResource(
        Res.string.label_remove
    ),
    labelReps: String = stringResource(
        Res.string.execution_label_reps
    ),
    labelUpdate: String = stringResource(
        Res.string.label_update
    ),
    labelWeight: String = stringResource(
        Res.string.execution_label_weight
    )
) = NewExecutionProps(
    labelCreate,
    labelNewExecution,
    labelNotes,
    labelRemove,
    labelReps,
    labelUpdate,
    labelWeight,
    onCreate,
    onRemove,
    onToggleDialog
)
