package br.com.asoncsts.multi.gymtrack.data.user.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.asoncsts.multi.gymtrack.extension.orUuidHexString
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
            .orUuidHexString()
    )
}
