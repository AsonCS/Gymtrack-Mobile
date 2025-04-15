package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.*
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

data class WorkoutWithExerciseExecutions(
    @Embedded
    val workout: WorkoutEntity,
    @Relation(
        associateBy = Junction(
            WorkoutWithExerciseExecutionsCrossRefEntity::class
        ),
        entityColumn = "exerciseExecutionId",
        parentColumn = "workoutId"
    )
    val exerciseExecutions: List<ExerciseExecutionEntity>
) {
    fun toWorkout(): Workout {
        return Workout(
            description = workout.description,
            exerciseExecutions = exerciseExecutions
                .map { it.toExerciseExecution() },
            id = workout.workoutId,
            name = workout.name
        )
    }
}
