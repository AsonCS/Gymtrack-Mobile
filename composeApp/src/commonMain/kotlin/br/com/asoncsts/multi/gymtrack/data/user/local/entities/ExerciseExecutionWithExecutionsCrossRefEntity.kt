package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import androidx.room.Index
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

@Entity(
    indices = [Index(value = ["executionId", "exerciseExecutionId"])],
    primaryKeys = ["executionId", "exerciseExecutionId"],
    tableName = "exercise_execution_with_executions_cross_ref_entity"
)
data class ExerciseExecutionWithExecutionsCrossRefEntity(
    val executionId: String,
    val exerciseExecutionId: String
) {
    constructor(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ) : this(
        executionId = execution.id,
        exerciseExecutionId = exerciseExecution.id
    )
}
