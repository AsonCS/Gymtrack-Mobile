package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

@Entity(
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
