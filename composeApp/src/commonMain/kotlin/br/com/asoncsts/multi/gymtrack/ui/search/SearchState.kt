package br.com.asoncsts.multi.gymtrack.ui.search

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

internal sealed class SearchState {
    data object Loading : SearchState()

    data class Success(
        val exercises: List<Exercise>
    ) : SearchState()

    data class Error(
        val throwable: Throwable
    ) : SearchState()
}
