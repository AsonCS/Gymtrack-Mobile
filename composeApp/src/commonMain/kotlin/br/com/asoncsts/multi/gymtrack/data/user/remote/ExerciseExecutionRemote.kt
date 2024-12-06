package br.com.asoncsts.multi.gymtrack.data.user.remote

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.user.api.ExerciseExecutionApi
import br.com.asoncsts.multi.gymtrack.data.user.remote.model.ExerciseExecutionSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface ExerciseExecutionRemote {

    class Impl(
        private val api: ExerciseExecutionApi,
        private val client: HttpClient
    ) : ExerciseExecutionRemote {

        override suspend fun getExerciseExecution(
            id: String
        ): Response<ExerciseExecutionSource> {
            return client.get {
                url {
                    takeFrom(api.exerciseExecution(id))
                }
            }.body()
        }

        override suspend fun getExerciseExecutions(
            ids: List<String>
        ): Response<List<ExerciseExecutionSource>> {
            return client.get {
                url {
                    takeFrom(api.exerciseExecutions(ids))
                }
            }.body()
        }

    }

    suspend fun getExerciseExecution(
        id: String
    ): Response<ExerciseExecutionSource>

    suspend fun getExerciseExecutions(
        ids: List<String>
    ): Response<List<ExerciseExecutionSource>>

}
