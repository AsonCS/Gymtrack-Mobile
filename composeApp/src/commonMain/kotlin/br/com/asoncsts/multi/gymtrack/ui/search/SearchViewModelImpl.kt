package br.com.asoncsts.multi.gymtrack.ui.search

import androidx.lifecycle.viewModelScope
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModelImpl(
    private val repository: ExerciseRepository,
    scope: CoroutineScope? = null
) : SearchViewModel() {

    private val scope: CoroutineScope = scope
        ?: viewModelScope

    private val _state = MutableStateFlow<SearchState>(SearchState.Loading)
    override val state = _state.asStateFlow()

    override fun getExercises(
        force: Boolean
    ) {
        if (_state.value is SearchState.Success && !force) return

        scope.launch {
            when (val result = repository.getExercises()) {
                is Wrapper.Error -> {
                    _state.update {
                        SearchState.Error(
                            result.error
                        )
                    }
                }

                is Wrapper.Success -> {
                    _state.update {
                        SearchState.Success(
                            result.data
                        )
                    }
                }
            }
        }
    }

}
