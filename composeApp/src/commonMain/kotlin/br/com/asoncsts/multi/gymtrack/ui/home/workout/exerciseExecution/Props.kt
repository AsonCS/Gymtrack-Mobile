package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.compose.runtime.Composable
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.label_name
import org.jetbrains.compose.resources.stringResource

internal data class Props(
    val labelNewExecution: String,
    val navigateUp: () -> Unit
)

@Composable
internal fun props(
    navigateUp: () -> Unit,
    labelNewExecution: String = stringResource(
        Res.string.label_name
    )
) = Props(
    labelNewExecution,
    navigateUp
)
