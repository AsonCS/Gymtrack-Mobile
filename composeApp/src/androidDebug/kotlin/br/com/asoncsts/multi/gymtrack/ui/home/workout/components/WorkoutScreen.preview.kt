package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack._mock.data.user.workout.WorkoutMock
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState

@PreviewMedPhone
@Composable
private fun Preview(
    @PreviewParameter(WorkoutScreenStateValuesProvider::class) state: WorkoutState
) {
    PreviewContainer {
        WorkoutScreen(
            workoutScreenProps(
                addNewExerciseExecution = {},
                clearExerciseExecution = {},
                navigateToExerciseExecution = {},
                navigateUp = {},
                WorkoutMock.workouts.first()
            ),
            null,
            state
        )
    }
}

private class WorkoutScreenStateValuesProvider : PreviewParameterProvider<WorkoutState> {
    override val values = workoutScreenStateSequence()
}
