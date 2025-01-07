package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.asoncsts.multi.gymtrack.extension.orUuidHexString
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

@Entity(tableName = "exercise_execution")
data class ExerciseExecutionEntity(
    val description: String?,
    val exerciseAlias: String,
    @PrimaryKey
    val exerciseExecutionId: String,
    val name: String
) {

    constructor(
        exerciseExecution: ExerciseExecution.Detail
    ) : this(
        description = exerciseExecution.description,
        exerciseAlias = exerciseExecution.exercise.alias,
        exerciseExecutionId = exerciseExecution.id
            .orUuidHexString(),
        name = exerciseExecution.name
    )

    fun toExerciseExecution(
        getExercise: (alias: String) -> Exercise
    ): ExerciseExecution {
        return ExerciseExecution.Impl(
            exercise = getExercise(
                exerciseAlias
            ),
            id = exerciseExecutionId,
            name = name
        )
    }
}
