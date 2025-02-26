package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

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

@Composable
internal fun NewExecutionDialog(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    props: NewExecutionProps,
    stateFields: StateFields,
    modifier: Modifier = Modifier
) {
    if (isVisible.not()) return

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
                modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(16.dp)
            ) {
                ScreenTopBar(
                    onDismissRequest,
                    props.labelNewExecution,
                    useArrowBack = false
                )

                TextField(
                    KeyboardType.Text,
                    props.labelNotes,
                    { stateFields.notes = it },
                    props.labelNotes,
                    stateFields.notes,
                    Modifier
                        .fillMaxWidth(),
                    capitalization = KeyboardCapitalization.Sentences
                )

                TextField(
                    KeyboardType.Number,
                    props.labelReps,
                    { stateFields.reps = it },
                    props.labelReps,
                    stateFields.reps,
                    Modifier
                        .fillMaxWidth()
                )

                TextField(
                    KeyboardType.Number,
                    props.labelWeight,
                    { stateFields.weight = it },
                    props.labelWeight,
                    stateFields.weight,
                    Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}
