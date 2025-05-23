package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.execution.EditStateFields
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

        private val _stateFields: MutableStateFlow<EditStateFields> by lazy {
            MutableStateFlow(
                EditStateFields { update ->
                    _stateFields.update { currentState ->
                        update(currentState)
                    }
                }
            )
        }
        override val stateFields = _stateFields.asStateFlow()

        private val exerciseExecution: ExerciseExecution.Detail
            get() = (_state.value as? ExerciseExecutionState.Success)
                ?.exerciseExecution
                ?: let {
                    throw Throwable("ExerciseExecution is null")
                }

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

        override suspend fun onExecutionConfirm() {
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
            val result = executionRepo.deleteExecution(
                executionId,
                exerciseExecution
            )
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

        override suspend fun onExecutionToggleDialog(
            execution: Execution?
        ) {
            _stateFields.update { state ->
                state.copy(
                    _notes = execution
                        ?.notes,
                    _order = execution
                        ?.order
                        ?.let { it + 1 }
                        ?: exerciseExecution
                            .executions
                            .maxOfOrNull { it.order + 2 }
                        ?: 1,
                    _reps = execution
                        ?.reps,
                    _weight = execution
                        ?.weight,
                    id = execution
                        ?.id,
                    idParent = execution
                        ?.idParent,
                    isDialogVisible = state.isDialogVisible.not()
                )
            }
        }

    }

    internal abstract val shared: SharedFlow<ExerciseExecutionShared>
    internal abstract val state: StateFlow<ExerciseExecutionState>
    internal abstract val stateFields: StateFlow<EditStateFields>

    internal abstract suspend fun getExerciseExecution(
        id: String
    )

    internal abstract suspend fun onExecutionConfirm()

    internal abstract suspend fun onExecutionRemove(
        executionId: String
    )

    internal abstract suspend fun onExecutionToggleDialog(
        execution: Execution?
    )

}
