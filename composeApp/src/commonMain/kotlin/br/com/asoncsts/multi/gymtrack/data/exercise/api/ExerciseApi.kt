package br.com.asoncsts.multi.gymtrack.data.exercise.api

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.model.ExerciseSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ExerciseApi {

    class Impl(
        private val client: HttpClient,
        private val host: String
    ) : ExerciseApi {

        override suspend fun exercise(
            idOrAlias: String
        ) = client
            .get("$host/exercises/$idOrAlias")
            .body<Response<ExerciseSource>>()

        override suspend fun exercises() = client
            .get("$host/exercises")
            .body<Response<List<ExerciseSource>>>()

    }

    suspend fun exercise(
        idOrAlias: String
    ): Response<ExerciseSource>

    suspend fun exercises(): Response<List<ExerciseSource>>

}
