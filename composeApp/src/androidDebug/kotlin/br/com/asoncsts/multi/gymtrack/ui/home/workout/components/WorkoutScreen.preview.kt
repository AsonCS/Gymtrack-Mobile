package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack._mock.data.user.workout.WorkoutMock
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
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
                WorkoutMock.workouts.first()
            ),
            null,
            state
        )
    }
}

@Composable
private fun workoutScreenProps(
    workout: Workout
) = workoutScreenProps(
    addNewExerciseExecution = {},
    labelAddExerciseExecution = stringResource(
        R.string.workout_label_add_exercise_execution
    ),
    labelNewExerciseExecution = stringResource(
        R.string.workout_label_new_exercise_execution
    ),
    navigateToExerciseExecution = {},
    navigateToNewExerciseExecution = {},
    toastAddNewExerciseExecutionError = stringResource(
        R.string.toast_insert_error
    ),
    toastAddNewExerciseExecutionSuccess = stringResource(
        R.string.workout_toast_add_exercise_execution_success
    ),
    workout = workout
)

private class WorkoutScreenStateValuesProvider : PreviewParameterProvider<WorkoutState> {
    override val values = workoutScreenStateSequence()
}
