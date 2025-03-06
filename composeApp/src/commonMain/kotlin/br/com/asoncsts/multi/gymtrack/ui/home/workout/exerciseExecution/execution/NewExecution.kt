package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._components.NewElementButton

@Composable
internal fun NewExecution(
    props: NewExecutionProps,
    stateFields: StateFields,
    modifier: Modifier = Modifier,
    initialDialogVisibility: Boolean = false
) {
    var dialogIsVisible by remember {
        mutableStateOf(initialDialogVisibility)
    }

    NewExecutionDialog(
        dialogIsVisible,
        onDismissRequest = {
            dialogIsVisible = false
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
                dialogIsVisible = true
            }
        )
    }
}
