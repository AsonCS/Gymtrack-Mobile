package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer

@PreviewComponent
@Composable
private fun NewExecutionDialogPreview(
    @PreviewParameter(NewExecutionDialogValuesProvider::class) state: StateFields
) {
    PreviewContainer {
        NewExecutionDialog(
            isVisible = true,
            onDismissRequest = {},
            props = newExecutionProps({}, {}, {}),
            stateFields = state
        )
    }
}

private class NewExecutionDialogValuesProvider : PreviewParameterProvider<StateFields> {
    override val values = newExecutionDialogSequence()
}
