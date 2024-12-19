package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

sealed class NewWorkoutState {
    data object Loading : NewWorkoutState()

    data class Success(
        val exerciseExecutions: List<ExerciseExecution.SimpleView>
    ) : NewWorkoutState()

    data class Error(
        val throwable: Throwable
    ) : NewWorkoutState()
}
