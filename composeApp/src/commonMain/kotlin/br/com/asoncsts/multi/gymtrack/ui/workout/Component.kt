package br.com.asoncsts.multi.gymtrack.ui.workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.Toast
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonAdd
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.new_workout_label_new
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EditWorkout(
    navigateToWorkout: (Workout) -> Unit,
    viewModel: WorkoutViewModel,
    modifier: Modifier = Modifier
) {
    val sharedState by viewModel
        .shared
        .collectAsState(null)
    val stateFields by viewModel
        .stateFields
        .collectAsState()

    EditWorkoutDialog(
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
                Res.string.new_workout_label_new
            ),
            onClick = {
                stateFields.onCreate()
            }
        )
    }

    sharedState.let { shared ->
        when (shared) {
            is Shared.ErrorOnCreateOrEditWorkout -> {
                Toast(
                    shared.message
                )
            }

            is Shared.ErrorOnDeleteWorkout -> {
                Toast(
                    shared.message
                )
            }

            is Shared.NavigateToWorkout -> {
                navigateToWorkout(
                    shared.workout
                )
            }

            Shared.SuccessOnDeleteWorkout,
            Shared.SuccessOnEditWorkout,
            null -> {
            }
        }
    }
}
