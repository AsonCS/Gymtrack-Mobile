package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

sealed class ExerciseExecutionShared {
    data class ErrorOnEditExecution(
        val message: String
    ) : ExerciseExecutionShared()

    data object SuccessOnEditExecution : ExerciseExecutionShared()
}
