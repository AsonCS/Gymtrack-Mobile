package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

sealed class NewWorkoutState {
    data object Loading : NewWorkoutState()

    data class Success(
        val exerciseExecutions: List<ExerciseExecution.SimpleView>
    ) : NewWorkoutState()

    data class SuccessNewWorkout(
        val workout: Workout
    ) : NewWorkoutState()

    data class Error(
        val throwable: Throwable
    ) : NewWorkoutState()
}
