package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import br.com.asoncsts.multi.gymtrack.ui._components.EditDialog
import br.com.asoncsts.multi.gymtrack.ui._components.TextField
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EditExecutionDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    onRemove: (
        executionId: String
    ) -> Unit,
    stateFields: EditStateFields
) {
    EditDialog(
        buttonNegative = if (stateFields.idParent == null && stateFields.id != null)
            Pair(
                stringResource(
                    Res.string.label_remove
                )
            ) {
                onRemove(
                    stateFields.id
                )
            }
        else null,
        buttonPositive = Pair(
            stringResource(
                when {
                    stateFields.idParent != null ->
                        Res.string.label_finish

                    stateFields.id != null ->
                        Res.string.label_update

                    else ->
                        Res.string.label_create
                }
            ),
            onConfirm
        ),
        onDismissRequest = onDismissRequest,
        stateFields.isDialogVisible
    ) {
        if (stateFields.idParent == null) {
            val labelOrder = stringResource(
                Res.string.execution_label_order
            )
            TextField(
                KeyboardType.Number,
                labelOrder,
                { stateFields.order = it },
                labelOrder,
                stateFields.order,
                Modifier
                    .fillMaxWidth()
            )

            val labelNotes = stringResource(
                Res.string.execution_label_notes
            )
            TextField(
                KeyboardType.Text,
                labelNotes,
                { stateFields.notes = it },
                labelNotes,
                stateFields.notes,
                Modifier
                    .fillMaxWidth(),
                capitalization = KeyboardCapitalization.Sentences
            )
        }

        val labelReps = stringResource(
            Res.string.execution_label_reps
        )
        TextField(
            KeyboardType.Number,
            labelReps,
            { stateFields.reps = it },
            labelReps,
            stateFields.reps,
            Modifier
                .fillMaxWidth()
        )

        val labelWeight = stringResource(
            Res.string.execution_label_weight
        )
        TextField(
            KeyboardType.Number,
            labelWeight,
            { stateFields.weight = it },
            labelWeight,
            stateFields.weight,
            Modifier
                .fillMaxWidth(),
            onDone = {
                onConfirm()
            }
        )
    }
}

internal fun editExecutionDialogSequence() = sequenceOf(
    EditStateFields {},
    EditStateFields(
        "notes",
        1,
        12,
        72.5,
        null,
        null,
        true
    ) {},
)
