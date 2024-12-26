package br.com.asoncsts.multi.gymtrack.data.user.api

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.user.remote.model.ExerciseExecutionSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface ExerciseExecutionApi {

    class Impl(
        private val client: HttpClient,
        private val host: String
    ) : ExerciseExecutionApi {

        override suspend fun exerciseExecution(
            id: String
        ) = client
            .get("$host/user/exercise-executions/$id")
            .body<Response<ExerciseExecutionSource>>()

        override suspend fun exerciseExecutionsGet(
            ids: List<String>
        ) = client
            .get("$host/user/exercise-executions?ids=${ids.joinToString(",")}")
            .body<Response<List<ExerciseExecutionSource>>>()

        override suspend fun exerciseExecutionsPost(
            ids: List<String>
        ) = client
            .post("$host/user/exercise-executions") {
                contentType(ContentType.Application.Json)
                setBody(ids)
            }.body<Response<List<ExerciseExecutionSource>>>()

        override suspend fun getExerciseExecutions() = client
            .get("$host/user/exercise-executions")
            .body<Response<List<ExerciseExecutionSource>>>()
    }

    suspend fun exerciseExecution(
        id: String
    ): Response<ExerciseExecutionSource>

    suspend fun exerciseExecutionsGet(
        ids: List<String>
    ): Response<List<ExerciseExecutionSource>>

    suspend fun exerciseExecutionsPost(
        ids: List<String>
    ): Response<List<ExerciseExecutionSource>>

    suspend fun getExerciseExecutions(): Response<List<ExerciseExecutionSource>>

}
