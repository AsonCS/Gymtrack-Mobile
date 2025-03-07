package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.Embedded
import androidx.room.Relation
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

data class ExerciseExecutionWithExecutions(
    @Embedded
    val exerciseExecution: ExerciseExecutionEntity,
    @Relation(
        entityColumn = "executionId",
        parentColumn = "exerciseExecutionId"
    )
    val executions: List<ExecutionEntity>
) {
    fun toExerciseExecution(): ExerciseExecution.Detail {
        return ExerciseExecution.Detail(
            description = exerciseExecution.description,
            exercise = exerciseExecution
                .exerciseAlias
                ?.let {
                    Exercise.Impl(it)
                },
            id = exerciseExecution.exerciseExecutionId,
            name = exerciseExecution.name,
            executions = executions
                .map { it.toExecution() }
        )
    }
}
