package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

// https://developer.android.com/training/data-storage/room/relationships/many-to-many
@Entity(tableName = "workout")
data class WorkoutEntity(
    val description: String?,
    val name: String,
    @PrimaryKey
    val workoutId: String
) {

    constructor(
        workout: Workout
    ) : this(
        description = workout.description,
        name = workout.name,
        workoutId = workout.id
    )

    data class WorkoutWithExerciseExecutions(
        @Embedded
        val workout: WorkoutEntity,
        @Relation(
            associateBy = Junction(
                ExerciseExecutionWorkoutCrossRefEntity::class
            ),
            entityColumn = "exerciseExecutionId",
            parentColumn = "workoutId"
        )
        val exerciseExecutions: List<ExerciseExecutionEntity>
    ) {
        fun toWorkout(): Workout {
            return Workout(
                description = workout.description,
                exerciseExecutionIds = exerciseExecutions
                    .map { it.exerciseExecutionId },
                id = workout.workoutId,
                name = workout.name
            )
        }
    }
}
