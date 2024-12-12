package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack._mock.data.user.workout.WorkoutMock
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer

@PreviewComponent
@Composable
private fun Preview() {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        Workout(
            workoutProps(
                labelAmount = stringResource(
                    R.string.home_label_amount,
                    WorkoutMock
                        .workouts[0]
                        .amount
                ),
                navigateToWorkout = {},
                workout = WorkoutMock
                    .workouts[0]
            )
        )
    }
}
