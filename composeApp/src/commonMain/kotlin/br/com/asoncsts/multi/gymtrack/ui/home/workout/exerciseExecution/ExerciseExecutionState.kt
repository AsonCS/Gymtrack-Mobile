package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

sealed class ExerciseExecutionState {
    data object Loading : ExerciseExecutionState()

    data class Success(
        val exercise: Exercise,
        val exerciseExecution: ExerciseExecution.Detail
    ) : ExerciseExecutionState()

    data class Error(
        val throwable: Throwable
    ) : ExerciseExecutionState()
}
