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
    val executionIdParent: String?,
    val exerciseExecutionId: String,
    val notes: String?,
    val order: Int,
    val reps: Int,
    val updated: Long,
    val weight: Double
) {

    constructor(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ) : this(
        executionId = execution.id
            .orUuidHexString(),
        executionIdParent = execution.idParent,
        exerciseExecutionId = exerciseExecution.id,
        notes = execution.notes,
        order = execution.order,
        reps = execution.reps,
        updated = execution.updated,
        weight = execution.weight
    )

    constructor(
        executionId: String
    ) : this(
        executionId = executionId,
        executionIdParent = null,
        exerciseExecutionId = "",
        notes = null,
        order = 0,
        reps = 0,
        updated = 0,
        weight = 0.0
    )

    fun toExecution(
        children: List<Execution>
    ): Execution {
        return Execution(
            children = children,
            id = executionId,
            idParent = executionIdParent,
            notes = notes,
            order = order,
            reps = reps,
            updated = updated,
            weight = weight
        )
    }

    override fun hashCode() = executionId.hashCode()

    override fun equals(
        other: Any?
    ) = (other as? ExecutionEntity)
        ?.executionId == executionId

}
