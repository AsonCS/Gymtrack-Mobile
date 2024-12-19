package br.com.asoncsts.multi.gymtrack.data.user.remote.model

import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSource(
    @SerialName("description")
    val description: String? = null,
    @SerialName("exerciseExecutionIds")
    val exerciseExecutionIds: List<String>? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null
) {

    constructor(
        workout: Workout
    ) : this(
        description = workout.description,
        exerciseExecutionIds = workout.exerciseExecutionIds,
        id = workout.id
            .takeIf { it.isNotBlank() },
        name = workout.name
    )

    fun toWorkout(): Workout {
        return Workout(
            description = description,
            exerciseExecutionIds = exerciseExecutionIds
                ?: emptyList(),
            id = id
                ?.takeIf { it.isNotBlank() }
                ?: throw IllegalStateException("Id is null"),
            name = name
                ?: ""
        )
    }

}
