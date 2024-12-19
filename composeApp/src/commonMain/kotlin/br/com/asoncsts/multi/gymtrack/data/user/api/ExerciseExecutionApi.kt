package br.com.asoncsts.multi.gymtrack.data.user.api

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.user.remote.model.ExerciseExecutionSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ExerciseExecutionApi {

    class Impl(
        private val client: HttpClient,
        private val host: String
    ) : ExerciseExecutionApi {

        override fun exerciseExecution(
            id: String
        ) = "$host/user/exercise-executions/$id"

        override fun exerciseExecutionsGet(
            ids: List<String>
        ) = "$host/user/exercise-executions?ids=${ids.joinToString(",")}"

        override fun exerciseExecutionsPost() = "$host/user/exercise-executions"

        override suspend fun getExerciseExecutions() = client
            .get("$host/user/exercise-executions")
            .body<Response<List<ExerciseExecutionSource>>>()
    }

    fun exerciseExecution(
        id: String
    ): String

    fun exerciseExecutionsGet(
        ids: List<String>
    ): String

    fun exerciseExecutionsPost(): String

    suspend fun getExerciseExecutions(): Response<List<ExerciseExecutionSource>>

}
