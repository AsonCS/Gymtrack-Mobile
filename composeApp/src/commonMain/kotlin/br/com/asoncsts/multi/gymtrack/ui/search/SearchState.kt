package br.com.asoncsts.multi.gymtrack.ui.search

import br.com.asoncsts.multi.gymtrack.model.Exercise

internal sealed class SearchState {
    data object Loading : SearchState()

    data class Success(
        val exercises: List<Exercise>
    ) : SearchState()

    data class Error(
        val throwable: Throwable
    ) : SearchState()
}
