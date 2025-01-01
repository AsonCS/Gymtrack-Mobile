package br.com.asoncsts.multi.gymtrack.data.exercise.remote.model

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSource(
    @SerialName("alias")
    val alias: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: String? = null
) {
    fun toExercise(
        hostImage: String
    ): Exercise {
        return Exercise.Impl(
            alias = alias
                ?: throw IllegalStateException("Alias is null"),
            image = if (image != null)
                "$hostImage/$image"
            else
                null,
            title = title
                ?: throw IllegalStateException("Title is null")
        )
    }

    fun toExerciseDetail(
        hostImage: String
    ): Exercise.Detail {
        val exercise = toExercise(hostImage)
        return Exercise.Detail(
            alias = exercise.alias,
            description = description ?: "",
            image = exercise.image,
            title = exercise.title,
            video = video
        )
    }
}
