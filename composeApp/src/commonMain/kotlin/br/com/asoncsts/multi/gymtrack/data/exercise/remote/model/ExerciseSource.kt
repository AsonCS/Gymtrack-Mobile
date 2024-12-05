package br.com.asoncsts.multi.gymtrack.data.exercise.remote.model

import br.com.asoncsts.multi.gymtrack.model.Exercise
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSource(
    @SerialName("alias")
    val alias: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("description_pt_br")
    val descriptionPtBr: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("title_pt_br")
    val titlePtBr: String? = null,
    @SerialName("video")
    val video: String? = null
) {
    fun toExercise(
        lang: DeviceLanguage
    ): Exercise {
        val title = when (lang) {
            DeviceLanguage.PT_BR -> titlePtBr
            else -> title
        } ?: title ?: throw IllegalStateException("Alias is null")
        return Exercise.Impl(
            alias = alias
                ?: throw IllegalStateException("Alias is null"),
            image = image,
            title = title
        )
    }

    fun toExerciseDetail(
        lang: DeviceLanguage
    ): Exercise.Detail {
        val exercise = toExercise(lang)
        val description = when (lang) {
            DeviceLanguage.PT_BR -> descriptionPtBr
            else -> description
        } ?: description ?: ""
        return Exercise.Detail(
            alias = exercise.alias,
            description = description,
            image = exercise.image,
            title = exercise.title,
            video = video
        )
    }
}
