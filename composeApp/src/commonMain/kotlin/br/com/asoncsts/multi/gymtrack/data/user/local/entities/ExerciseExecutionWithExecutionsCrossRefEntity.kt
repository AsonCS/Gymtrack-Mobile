package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity

@Entity(
    primaryKeys = ["executionId", "exerciseExecutionId"],
    tableName = "exercise_execution_with_executions_cross_ref_entity"
)
data class ExerciseExecutionWithExecutionsCrossRefEntity(
    val executionId: String,
    val exerciseExecutionId: String
)
