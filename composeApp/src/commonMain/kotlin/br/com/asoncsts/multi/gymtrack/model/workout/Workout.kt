package br.com.asoncsts.multi.gymtrack.model.workout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

data class Workout(
    val description: String?,
    val exerciseExecutions: List<ExerciseExecution>,
    val id: String,
    val name: String
) {
    val amount: String
        get() = exerciseExecutions.size.toString()
}
