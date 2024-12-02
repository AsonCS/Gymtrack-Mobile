package br.com.asoncsts.multi.gymtrack.data.exercise.remote.model

import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSource(
    @SerialName("description")
    val description: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("title")
    val title: String? = null
) {
    fun toExercise() = Exercise(
        description = description
            ?: "",
        id = id
            ?: "",
        image = image,
        title = title
            ?: ""
    )
}
