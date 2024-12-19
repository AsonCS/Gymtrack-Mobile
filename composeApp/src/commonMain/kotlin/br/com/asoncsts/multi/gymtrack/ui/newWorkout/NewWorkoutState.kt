package br.com.asoncsts.multi.gymtrack.ui.newWorkout

sealed class NewWorkoutState {
    data object Loading : NewWorkoutState()

    data object Success : NewWorkoutState()

    data class Error(
        val throwable: Throwable
    ) : NewWorkoutState()
}
