package br.com.asoncsts.multi.gymtrack.data.exercise.remote.model

import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseSource(
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
    val titlePtBr: String? = null
) {
    fun toExercise(
        lang: DeviceLanguage
    ): Exercise {
        val description = when (lang) {
            DeviceLanguage.PT_BR -> descriptionPtBr
            else -> description
        } ?: description ?: ""
        val title = when (lang) {
            DeviceLanguage.PT_BR -> titlePtBr
            else -> title
        } ?: title ?: ""
        return Exercise(
            description = description,
            id = id
                ?: "",
            image = image,
            title = title
        )
    }
}
