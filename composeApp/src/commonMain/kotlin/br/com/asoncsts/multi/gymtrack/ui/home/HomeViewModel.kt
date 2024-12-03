package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.lifecycle.viewModelScope
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.extension.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val repository: ExerciseRepository,
    scope: CoroutineScope? = null
) : HomeViewModel() {

    private val scope: CoroutineScope = scope
        ?: viewModelScope

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    override val state = _state.asStateFlow()

    override fun getExercise(
        id: String,
        force: Boolean
    ) {
        scope.launch {
            when (val result = repository.getExercise(id)) {
                is Wrapper.Error -> {
                    TAG_HOME.error("getExercise", result.error)
                }

                is Wrapper.Success -> {
                    TAG_HOME.log("getExercise: ${result.data}")
                }
            }
        }
    }

    override fun getExercises(
        force: Boolean
    ) {
        if (_state.value is HomeState.Success && !force) return

        scope.launch {
            when (val result = repository.getExercises()) {
                is Wrapper.Error -> {
                    _state.update {
                        HomeState.Error(
                            result.error
                        )
                    }
                }

                is Wrapper.Success -> {
                    _state.update {
                        HomeState.Success(
                            result.data
                        )
                    }
                }
            }
        }
    }

}
