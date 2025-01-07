package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity

@Entity(
    primaryKeys = ["exerciseExecutionId", "workoutId"],
    tableName = "workout_with_exercise_executions_cross_ref"
)
data class WorkoutWithExerciseExecutionsCrossRefEntity(
    val exerciseExecutionId: String,
    val workoutId: String
)
