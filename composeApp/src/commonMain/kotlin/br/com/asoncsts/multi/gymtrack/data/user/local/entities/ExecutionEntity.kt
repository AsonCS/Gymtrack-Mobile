package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.asoncsts.multi.gymtrack.extension.orUuidHexString
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

@Entity(tableName = "execution")
data class ExecutionEntity(
    @PrimaryKey
    val executionId: String,
    val exerciseExecutionId: String,
    val notes: String?,
    val reps: Int,
    val weight: Double
) {

    constructor(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ) : this(
        executionId = execution.id
            .orUuidHexString(),
        exerciseExecutionId = exerciseExecution.id,
        notes = execution.notes,
        reps = execution.reps,
        weight = execution.weight
    )

    fun toExecution(): Execution {
        return Execution(
            id = executionId,
            notes = notes,
            reps = reps,
            weight = weight
        )
    }

}
