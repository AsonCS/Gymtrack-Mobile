package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.Entity

@Entity(
    primaryKeys = ["exerciseExecutionId", "workoutId"],
    tableName = "exercise_execution_workout_cross_ref"
)
data class ExerciseExecutionWorkoutCrossRefEntity(
    val exerciseExecutionId: String,
    val workoutId: String
)
