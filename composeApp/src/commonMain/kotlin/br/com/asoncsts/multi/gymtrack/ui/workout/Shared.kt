package br.com.asoncsts.multi.gymtrack.ui.workout

import br.com.asoncsts.multi.gymtrack.model.workout.Workout

internal sealed class Shared {
    data class ErrorOnCreateWorkout(
        val message: String
    ) : Shared()

    data class NavigateToWorkout(
        val workout: Workout
    ) : Shared()
}
