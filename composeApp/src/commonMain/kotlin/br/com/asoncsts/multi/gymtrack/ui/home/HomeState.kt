package br.com.asoncsts.multi.gymtrack.ui.home

import br.com.asoncsts.multi.gymtrack.model.workout.Workout

sealed class HomeState {
    data object Loading : HomeState()

    data class Success(
        val workouts: List<Workout>
    ) : HomeState()

    data class Error(
        val throwable: Throwable
    ) : HomeState()
}
