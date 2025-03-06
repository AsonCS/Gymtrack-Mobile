package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone

@PreviewMedPhone
@Composable
private fun NewExecutionPreview(
    @PreviewParameter(NewExecutionValuesProvider::class) state: StateFields,
) {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        NewExecution(
            props = newExecutionProps({}),
            stateFields = state
        )
    }
}

@PreviewMedPhone
@Composable
private fun NewExecutionWithDialogPreview(
    @PreviewParameter(NewExecutionValuesProvider::class) state: StateFields,
) {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        NewExecution(
            props = newExecutionProps({}),
            stateFields = state,
            initialDialogVisibility = true
        )
    }
}

private class NewExecutionValuesProvider : PreviewParameterProvider<StateFields> {
    override val values = newExecutionDialogSequence()
}
