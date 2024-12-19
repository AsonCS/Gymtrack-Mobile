package br.com.asoncsts.multi.gymtrack.data.user.remote.model

import br.com.asoncsts.multi.gymtrack.data.exercise.remote.model.ExerciseSource
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseExecutionSource(
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
    fun toExerciseExecution(
        lang: DeviceLanguage
    ): ExerciseExecution {
        exercise ?: throw IllegalStateException("Exercise is null")
        val exercise = exercise.toExercise(lang)

        return ExerciseExecution.Impl(
            exercise = exercise,
            id = id
                ?: throw IllegalStateException("Id is null"),
            name = name
                ?: throw IllegalStateException("Name is null")
        )
    }

    fun toExerciseExecutionDetail(
        lang: DeviceLanguage
    ): ExerciseExecution.Detail {
        val exercise = toExerciseExecution(lang)

        return ExerciseExecution.Detail(
            description = description,
            exercise = exercise.exercise,
            id = exercise.id,
            name = exercise.name,
            executions = executions?.map {
                it.toExecution()
            } ?: emptyList()
        )
    }

    fun toSimpleView(): ExerciseExecution.SimpleView {
        return ExerciseExecution.SimpleView(
            id = id
                ?: throw IllegalStateException("Id is null"),
            name = name
                ?: throw IllegalStateException("Name is null")
        )
    }
}
