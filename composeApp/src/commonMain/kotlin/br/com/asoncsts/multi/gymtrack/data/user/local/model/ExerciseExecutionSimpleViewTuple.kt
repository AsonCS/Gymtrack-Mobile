package br.com.asoncsts.multi.gymtrack.data.user.local.model

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

data class ExerciseExecutionSimpleViewTuple(
    val exerciseExecutionId: String,
    val name: String
) {
    fun toExerciseExecution(): ExerciseExecution.SimpleView {
        return ExerciseExecution.SimpleView(
            id = exerciseExecutionId,
            name = name
        )
    }
}
