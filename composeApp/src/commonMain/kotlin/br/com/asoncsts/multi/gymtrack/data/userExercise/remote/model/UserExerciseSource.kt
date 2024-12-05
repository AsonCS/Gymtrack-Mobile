package br.com.asoncsts.multi.gymtrack.data.userExercise.remote.model

import br.com.asoncsts.multi.gymtrack.data.exercise.remote.model.ExerciseSource
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import br.com.asoncsts.multi.gymtrack.model.UserExercise
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserExerciseSource(
    @SerialName("description")
    val description: String? = null,
    @SerialName("executions")
    val executions: List<ExecutionSource>? = null,
    @SerialName("exercise")
    val exercise: ExerciseSource? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null
) {
    fun toUserExercise(
        lang: DeviceLanguage
    ): UserExercise {
        exercise ?: throw IllegalStateException("Exercise is null")
        val exercise = exercise.toExercise(lang)

        return UserExercise.Impl(
            exercise = exercise,
            id = id
                ?: throw IllegalStateException("Id is null"),
            name = name
                ?: throw IllegalStateException("Name is null")
        )
    }

    fun toUserExerciseDetail(
        lang: DeviceLanguage
    ): UserExercise.Detail {
        val exercise = toUserExercise(lang)

        return UserExercise.Detail(
            description = description,
            exercise = exercise.exercise,
            id = exercise.id,
            name = exercise.name,
            executions = executions?.map {
                it.toExecution()
            } ?: emptyList()
        )
    }
}
