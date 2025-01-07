package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState.*
import kotlinx.coroutines.flow.*

class WorkoutViewModelImpl(
    private val getExercise: (alias: String) -> Exercise,
    private val repo: ExerciseExecutionRepository
) : WorkoutViewModel() {

    private val _state = MutableStateFlow<WorkoutState>(Loading)
    override val state = _state.asStateFlow()

    override suspend fun getWorkout(
        workout: Workout
    ) {
        if (_state.value is Success) return

        when (val result = repo.getExerciseExecutions(
            getExercise,
            workout.exerciseExecutionIds
        )) {
            is Wrapper.Error -> {
                _state.update {
                    Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                result.data.collect { exercisesExecutions ->
                    _state.update {
                        Success(
                            exercisesExecutions
                        )
                    }
                }
            }
        }
    }
}
