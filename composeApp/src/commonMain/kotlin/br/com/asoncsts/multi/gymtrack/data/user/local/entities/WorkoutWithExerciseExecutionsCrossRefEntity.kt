package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import androidx.room.Index
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

@Entity(
    indices = [Index(value = ["exerciseExecutionId", "workoutId"])],
    primaryKeys = ["exerciseExecutionId", "workoutId"],
    tableName = "workout_with_exercise_executions_cross_ref"
)
data class WorkoutWithExerciseExecutionsCrossRefEntity(
    val exerciseExecutionId: String,
    val workoutId: String
) {
    constructor(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ) : this(
        exerciseExecutionId = exerciseExecution.id,
        workoutId = workout.id
    )
}
