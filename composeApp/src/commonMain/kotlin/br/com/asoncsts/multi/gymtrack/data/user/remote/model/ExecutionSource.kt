package br.com.asoncsts.multi.gymtrack.data.user.remote.model

import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExecutionSource(
    @SerialName("id")
    val id: String? = null,
    @SerialName("notes")
    val notes: String? = null,
    @SerialName("reps")
    val reps: Int? = null,
    @SerialName("weight")
    val weight: Int? = null
) {
    fun toExecution(): Execution {
        return Execution(
            id = id
                ?: throw IllegalStateException("Id is null"),
            notes = notes
                ?: "",
            reps = reps
                ?: 0,
            weight = weight
                ?: 0
        )
    }
}
