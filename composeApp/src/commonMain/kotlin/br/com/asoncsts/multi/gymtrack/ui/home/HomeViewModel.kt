package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class HomeViewModel : ViewModel() {

    class Impl(
        private val repository: ExerciseRepository,
        scope: CoroutineScope? = null
    ) : HomeViewModel() {

        private val scope: CoroutineScope = scope
            ?: viewModelScope

        private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
        override val state = _state.asStateFlow()

        override fun getExercises() {
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

    internal abstract val state: StateFlow<HomeState>

    internal abstract fun getExercises()

}
