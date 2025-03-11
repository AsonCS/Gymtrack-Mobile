package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution.StateFields
import kotlinx.coroutines.flow.*

abstract class ExerciseExecutionViewModel : ViewModel() {
    class Impl(
        private val exercisesSource: ExercisesSource,
        private val exerciseExecutionRepo: ExerciseExecutionRepository,
        private val executionRepo: ExecutionRepository,
    ) : ExerciseExecutionViewModel() {

        private val _shared = MutableSharedFlow<ExerciseExecutionShared>()
        override val shared = _shared.asSharedFlow()

        private val _state = MutableStateFlow<ExerciseExecutionState>(
            ExerciseExecutionState.Loading
        )
        override val state = _state.asStateFlow()

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

        override suspend fun getExerciseExecution(
            id: String
        ) {
            val result = exerciseExecutionRepo.getExerciseExecution(id)
            when (result) {
                is Wrapper.Error -> {
                    _state.update {
                        ExerciseExecutionState.Error(
                            result.error
                        )
                    }
                }

                is Wrapper.Success -> {
                    result.data.collect { exerciseExecution ->
                        _state.update {
                            ExerciseExecutionState.Success(
                                exerciseExecution.fillExercise(
                                    exercisesSource::getExercise
                                )
                            )
                        }
                    }
                }
            }
        }

        override suspend fun onExecutionAddOrUpdate(
            execution: Execution?
        ) {
            _stateFields.update {
                it.copy(
                    _notes = execution
                        ?.notes,
                    _reps = execution
                        ?.reps,
                    _weight = execution
                        ?.weight,
                    id = execution
                        ?.id,
                    isDialogVisible = it.isDialogVisible.not()
                )
            }
        }

        override suspend fun onExecutionConfirmChange() {
            val exerciseExecution = (_state.value as? ExerciseExecutionState.Success)
                ?.exerciseExecution
                ?: let {
                    _shared.emit(
                        ExerciseExecutionShared.ErrorOnEditExecution(
                            "ExerciseExecution is null"
                        )
                    )
                    return
                }

            val result = executionRepo.putExecution(
                _stateFields.value.toExecution(),
                exerciseExecution
            )
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        ExerciseExecutionShared.ErrorOnEditExecution(
                            result.error.message
                                ?: "Error on create execution"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _stateFields.update {
                        it.copy(
                            isDialogVisible = false
                        )
                    }
                    _shared.emit(
                        ExerciseExecutionShared.SuccessOnEditExecution
                    )
                }
            }
        }

        override suspend fun onExecutionRemove(
            executionId: String
        ) {
            val result = executionRepo.deleteExecution(executionId)
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        ExerciseExecutionShared.ErrorOnEditExecution(
                            result.error.message
                                ?: "Error on remove execution"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _stateFields.update {
                        it.copy(
                            isDialogVisible = false
                        )
                    }
                    _shared.emit(
                        ExerciseExecutionShared.SuccessOnEditExecution
                    )
                }
            }
        }

    }

    internal abstract val shared: SharedFlow<ExerciseExecutionShared>
    internal abstract val state: StateFlow<ExerciseExecutionState>
    internal abstract val stateFields: StateFlow<StateFields>

    internal abstract suspend fun getExerciseExecution(
        id: String
    )

    internal abstract suspend fun onExecutionAddOrUpdate(
        execution: Execution?
    )

    internal abstract suspend fun onExecutionConfirmChange()

    internal abstract suspend fun onExecutionRemove(
        executionId: String
    )
}
