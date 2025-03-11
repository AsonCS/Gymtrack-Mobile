package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._components.NewElementButton

@Composable
internal fun NewExecution(
    props: NewExecutionProps,
    stateFields: StateFields,
    modifier: Modifier = Modifier
) {
    NewExecutionDialog(
        stateFields.isDialogVisible,
        onDismissRequest = {
            props.onToggleDialog()
        },
        props,
        stateFields
    )

    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        NewElementButton(
            props.labelNewExecution,
            onClick = {
                props.onToggleDialog()
            }
        )
    }
}
