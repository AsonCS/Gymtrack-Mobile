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
    fun toWorkout(): Workout {
        return Workout.Impl(
            description = description,
            exerciseExecutionIds = exerciseExecutionIds ?: emptyList(),
            id = id
                ?: throw IllegalStateException("Id is null"),
            name = name
                ?: ""
        )
    }
}
