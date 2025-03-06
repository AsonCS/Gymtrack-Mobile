package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

sealed class ExerciseExecutionShared {
    data class ErrorOnCreateExecution(
        val message: String
    ) : ExerciseExecutionShared()

    data object SuccessOnCreateExecution : ExerciseExecutionShared()
}
