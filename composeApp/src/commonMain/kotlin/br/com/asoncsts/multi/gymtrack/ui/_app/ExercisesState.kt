package br.com.asoncsts.multi.gymtrack.ui._app

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

sealed class ExercisesState {
    data object Loading : ExercisesState()

    data class Success(
        val exercises: List<Exercise>
    ) : ExercisesState()

    data class Error(
        val throwable: Throwable
    ) : ExercisesState()
}
