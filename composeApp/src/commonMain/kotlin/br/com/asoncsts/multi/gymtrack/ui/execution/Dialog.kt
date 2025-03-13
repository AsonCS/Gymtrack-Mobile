package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.asoncsts.multi.gymtrack.ui._components.*
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes
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
    if (stateFields.isDialogVisible.not()) return

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        OutlinedCard(
            Modifier
                .fillMaxWidth(),
            border = border(),
            shape = shapes().medium
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(16.dp)
            ) {
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

                Row(
                    horizontalArrangement = Arrangement
                        .spacedBy(8.dp)
                ) {
                    Button(
                        if (stateFields.id != null)
                            stringResource(
                                Res.string.label_update
                            )
                        else
                            stringResource(
                                Res.string.label_create
                            ),
                        onClick = onConfirm
                    )
                    if (stateFields.id != null) {
                        Button(
                            stringResource(
                                Res.string.label_remove
                            ),
                            onClick = {
                                onRemove(
                                    stateFields.id
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

internal fun newExecutionDialogSequence() = sequenceOf(
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
