package br.com.asoncsts.multi.gymtrack.data.exercise.remote

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.model.ExerciseSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface ExerciseRemote {

    class Impl(
        private val api: ExerciseApi,
        private val client: HttpClient
    ) : ExerciseRemote {
        override suspend fun getExercises(): Response<List<ExerciseSource>> {
            return client.get {
                url {
                    takeFrom(api.exercises())
                }
            }.body()
        }
    }

    suspend fun getExercises(): Response<List<ExerciseSource>>

}
