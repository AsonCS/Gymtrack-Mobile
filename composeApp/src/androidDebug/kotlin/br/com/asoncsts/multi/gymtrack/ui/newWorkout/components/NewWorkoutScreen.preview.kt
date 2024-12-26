package br.com.asoncsts.multi.gymtrack.ui.newWorkout.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutState
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutStateFields

@PreviewMedPhone
@Composable
private fun Preview(
    @PreviewParameter(
        NewWorkoutStateValuesProvider::class
    ) state: NewWorkoutState
) {
    PreviewContainer {
        NewWorkoutScreen(
            newWorkoutScreenProps(
                labelDescription = stringResource(
                    R.string.new_workout_label_description
                ),
                labelName = stringResource(
                    R.string.new_workout_label_name
                ),
                labelNewWorkout = stringResource(
                    R.string.new_workout_label_new_workout
                ),
                onSave = {},
                placeholderDescription = stringResource(
                    R.string.new_workout_placeholder_description
                ),
                placeholderName = stringResource(
                    R.string.new_workout_placeholder_name
                )
            ),
            state,
            NewWorkoutStateFields {}
        )
    }
}

private class NewWorkoutStateValuesProvider
    : PreviewParameterProvider<NewWorkoutState> {
    override val values = newWorkoutStateValuesProvider
}
