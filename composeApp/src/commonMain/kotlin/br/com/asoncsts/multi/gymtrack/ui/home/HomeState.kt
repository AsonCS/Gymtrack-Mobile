package br.com.asoncsts.multi.gymtrack.ui.home

import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise

internal sealed class HomeState {
    data object Loading : HomeState()

    data class Success(
        val exercises: List<Exercise>
    ) : HomeState()

    data class Error(
        val throwable: Throwable
    ) : HomeState()
}
