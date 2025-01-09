package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

sealed class WorkoutState {
    data object Loading : WorkoutState()

    data class Success(
        val allExerciseExecutions: List<ExerciseExecution>,
        val filteredExerciseExecutions: List<ExerciseExecution>,
    ) : WorkoutState()
}
