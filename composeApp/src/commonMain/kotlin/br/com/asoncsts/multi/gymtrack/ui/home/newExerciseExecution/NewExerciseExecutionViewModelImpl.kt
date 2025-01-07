package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NewExerciseExecutionViewModelImpl(
    private val exercisesSource: ExercisesSource,
) : NewExerciseExecutionViewModel() {

    private val _state = MutableStateFlow<NewExerciseExecutionState>(
        NewExerciseExecutionState.Loading
    )
    override val state = _state

    private val _stateFields: MutableStateFlow<NewExerciseExecutionStateFields> by lazy {
        MutableStateFlow(
            NewExerciseExecutionStateFields { update ->
                _stateFields.update { currentState ->
                    update(currentState)
                }
            }
        )
    }
    override val stateFields = _stateFields

    override suspend fun getExercises() {
        val result = exercisesSource.getExercises()
        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    NewExerciseExecutionState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    NewExerciseExecutionState.Success(
                        result.data
                    )
                }
            }
        }
    }

    override suspend fun save() {
        TODO("Not yet implemented")
    }

}
