package br.com.asoncsts.multi.gymtrack.ui.workout

import br.com.asoncsts.multi.gymtrack.model.workout.Workout

internal sealed class Shared {
    data class ErrorOnCreateOrEditWorkout(
        val message: String
    ) : Shared()

    data class ErrorOnDeleteWorkout(
        val message: String
    ) : Shared()

    data object SuccessOnDeleteWorkout : Shared()

    data object SuccessOnEditWorkout : Shared()

    data class NavigateToWorkout(
        val workout: Workout
    ) : Shared()
}
