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
    fun toExercise(): Exercise {
        return Exercise.Impl(
            alias = alias
                ?: throw IllegalStateException("Alias is null"),
            image = image,
            title = title
                ?: throw IllegalStateException("Title is null")
        )
    }

    fun toExerciseDetail(): Exercise.Detail {
        val exercise = toExercise()
        return Exercise.Detail(
            alias = exercise.alias,
            description = description ?: "",
            image = exercise.image,
            title = exercise.title,
            video = video
        )
    }
}
