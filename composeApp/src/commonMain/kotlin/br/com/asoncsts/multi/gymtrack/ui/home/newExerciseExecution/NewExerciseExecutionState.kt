package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

sealed class NewExerciseExecutionState {
    data class Error(
        val throwable: Throwable
    ) : NewExerciseExecutionState()

    data object Loading : NewExerciseExecutionState()

    data class Success(
        val exercises: List<Exercise>
    ) : NewExerciseExecutionState()

    data class SuccessNewExerciseExecution(
        val exerciseExecution: ExerciseExecution
    ) : NewExerciseExecutionState()
}
