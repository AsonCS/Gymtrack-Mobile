package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class HomeViewModel(
    private val repository: ExerciseRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    internal val state = _state.asStateFlow()

    internal fun getExercises() {
        viewModelScope.launch {
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
