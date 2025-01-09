package br.com.asoncsts.multi.gymtrack.ui.home.workout

sealed class WorkoutShared {
    data class ErrorInit(
        val message: String
    ) : WorkoutShared()

    data class ErrorAddNewExerciseExecution(
        val message: String
    ) : WorkoutShared()

    data object SuccessAddNewExerciseExecution : WorkoutShared()
}
