package br.com.asoncsts.multi.gymtrack.ui.exerciseExecution

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonAdd
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.exercise_execution_label_new
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EditExerciseExecution(
    viewModel: ExerciseExecutionViewModel,
    modifier: Modifier = Modifier
) {
    val sharedState by viewModel
        .shared
        .collectAsState(null)
    val stateFields by viewModel
        .stateFields
        .collectAsState()

    EditExerciseExecutionDialog(
        onConfirm = {
            viewModel.launch {
                onConfirm()
            }
        },
        onDismissRequest = {
            stateFields.onClose()
        },
        stateFields
    )

    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        ButtonAdd(
            stringResource(
                Res.string.exercise_execution_label_new
            ),
            onClick = {
                stateFields.onCreate()
            }
        )
    }

    sharedState.let { shared ->
        when (shared) {
            else -> {
            }
        }
    }
}
