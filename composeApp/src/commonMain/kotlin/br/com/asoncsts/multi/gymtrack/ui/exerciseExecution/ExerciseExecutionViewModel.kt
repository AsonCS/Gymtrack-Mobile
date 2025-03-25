package br.com.asoncsts.multi.gymtrack.ui.exerciseExecution

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import kotlinx.coroutines.flow.*

abstract class ExerciseExecutionViewModel : ViewModel() {

    class Impl(
        private val exerciseExecutionRepo: ExerciseExecutionRepository
    ) : ExerciseExecutionViewModel() {

        private val _shared = MutableSharedFlow<Shared>()
        override val shared = _shared.asSharedFlow()

        private val _stateFields: MutableStateFlow<StateFields> by lazy {
            MutableStateFlow(
                StateFields { update ->
                    _stateFields.update { currentState ->
                        update(currentState)
                    }
                }
            )
        }
        override val stateFields = _stateFields.asStateFlow()

        override suspend fun onConfirm() {
            val isEdit = _stateFields.value.id != null
            val result = exerciseExecutionRepo.putExerciseExecution(
                _stateFields.value.toExerciseExecution()
            )
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        Shared.ErrorOnCreateOrEditExerciseExecution(
                            result.error.message
                                ?: "Error on create or edit exercise execution"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _shared.emit(
                        if (isEdit)
                            Shared.SuccessOnEditExerciseExecution
                        else
                            Shared.NavigateToExerciseExecution(
                                result.data
                            )
                    )
                }
            }
        }

        override suspend fun onDelete(
            exerciseExecution: ExerciseExecution
        ) {
            val result = exerciseExecutionRepo.deleteExerciseExecution(
                exerciseExecution
            )
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        Shared.ErrorOnDeleteExerciseExecution(
                            result.error.message
                                ?: "Error on delete exercise execution"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _shared.emit(
                        Shared.SuccessOnDeleteExerciseExecution
                    )
                }
            }
        }

    }

    internal abstract val shared: SharedFlow<Shared>
    internal abstract val stateFields: StateFlow<StateFields>

    internal abstract suspend fun onConfirm()

    internal abstract suspend fun onDelete(
        exerciseExecution: ExerciseExecution
    )

}
