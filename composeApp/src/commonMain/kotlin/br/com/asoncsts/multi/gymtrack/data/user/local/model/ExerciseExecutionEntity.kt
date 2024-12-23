package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_execution")
data class ExerciseExecutionEntity(
    val description: String?,
    @PrimaryKey
    val exerciseExecutionId: String,
    val exerciseId: String,
    val name: String
)
