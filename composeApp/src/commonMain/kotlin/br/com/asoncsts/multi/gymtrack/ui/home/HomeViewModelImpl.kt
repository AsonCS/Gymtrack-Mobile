package br.com.asoncsts.multi.gymtrack.ui.home

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState.*
import kotlinx.coroutines.flow.*

class HomeViewModelImpl(
    private val repo: WorkoutRepository
) : HomeViewModel() {

    private val _state = MutableStateFlow<HomeState>(Loading)
    override val state = _state.asStateFlow()

    override suspend fun getWorkouts() {
        if (_state.value is Success) return

        when (val result = repo.getWorkouts()) {
            is Wrapper.Error -> {
                _state.update {
                    Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    Success(
                        result.data
                    )
                }
            }
        }
    }
}
