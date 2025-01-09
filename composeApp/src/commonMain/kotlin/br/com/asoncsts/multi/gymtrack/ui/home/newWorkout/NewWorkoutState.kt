package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

sealed class NewWorkoutState {
    data class Error(
        val throwable: Throwable
    ) : NewWorkoutState()

    data object Loading : NewWorkoutState()

    data class Success(
        val exerciseExecutions: List<ExerciseExecution>
    ) : NewWorkoutState()

    data class SuccessNewWorkout(
        val workout: Workout
    ) : NewWorkoutState()
}
