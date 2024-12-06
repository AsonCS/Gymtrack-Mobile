package br.com.asoncsts.multi.gymtrack.model.workout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface Workout {
    val description: String?
    val exerciseExecutionIds: List<String>
    val id: String
    val name: String

    data class Impl(
        override val description: String?,
        override val exerciseExecutionIds: List<String>,
        override val id: String,
        override val name: String
    ) : Workout

    data class Detail(
        override val description: String?,
        override val id: String,
        override val name: String,
        val exerciseExecutions: List<ExerciseExecution>,
    ) : Workout {
        override val exerciseExecutionIds: List<String>
            get() = exerciseExecutions.map { it.id }
    }
}
