package br.com.asoncsts.multi.gymtrack.ui.workout

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.*

abstract class WorkoutViewModel : ViewModel() {

    class Impl(
        private val workoutRepo: WorkoutRepository
    ) : WorkoutViewModel() {

        private val _shared: MutableSharedFlow<Shared> by lazy {
            MutableSharedFlow()
        }
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
            val result = workoutRepo.putWorkout(
                _stateFields.value.toWorkout()
            )
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        Shared.ErrorOnCreateOrEditWorkout(
                            result.error.message
                                ?: "Error on create or edit workout"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _shared.emit(
                        if (isEdit)
                            Shared.SuccessOnEditWorkout
                        else
                            Shared.NavigateToWorkout(
                                result.data
                            )
                    )
                }
            }
        }

        override suspend fun onDelete(
            workout: Workout
        ) {
            val result = workoutRepo.deleteWorkout(
                workout
            )
            when (result) {
                is Wrapper.Error -> {
                    _shared.emit(
                        Shared.ErrorOnDeleteWorkout(
                            result.error.message
                                ?: "Error on delete workout"
                        )
                    )
                }

                is Wrapper.Success -> {
                    _shared.emit(
                        Shared.SuccessOnDeleteWorkout
                    )
                }
            }
        }

    }

    internal abstract val shared: SharedFlow<Shared>
    internal abstract val stateFields: StateFlow<StateFields>

    internal abstract suspend fun onConfirm()

    internal abstract suspend fun onDelete(
        workout: Workout
    )

}
