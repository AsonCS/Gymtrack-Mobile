package br.com.asoncsts.multi.gymtrack.ui.exerciseExecution

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

internal sealed class Shared {
    data class ErrorOnCreateOrEditExerciseExecution(
        val message: String
    ) : Shared()

    data class ErrorOnDeleteExerciseExecution(
        val message: String
    ) : Shared()

    data object SuccessOnDeleteExerciseExecution : Shared()

    data object SuccessOnEditExerciseExecution : Shared()

    data class NavigateToExerciseExecution(
        val exerciseExecution: ExerciseExecution
    ) : Shared()
}
