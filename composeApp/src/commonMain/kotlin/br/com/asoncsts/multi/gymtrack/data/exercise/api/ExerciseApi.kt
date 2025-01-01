package br.com.asoncsts.multi.gymtrack.data.exercise.api

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.model.ExerciseSource
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import br.com.asoncsts.multi.gymtrack.extension.deviceLanguage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ExerciseApi {

    class Impl(
        private val client: HttpClient,
        private val host: String,
        private val lang: () -> DeviceLanguage = ::deviceLanguage
    ) : ExerciseApi {

        override suspend fun exercise(
            idOrAlias: String
        ) = client
            .get("$host/exercises/$idOrAlias?lang=${lang().value}")
            .body<Response<ExerciseSource>>()

        override suspend fun exercises() = client
            .get("$host/exercises?lang=${lang().value}")
            .body<Response<List<ExerciseSource>>>()

    }

    suspend fun exercise(
        idOrAlias: String
    ): Response<ExerciseSource>

    suspend fun exercises(): Response<List<ExerciseSource>>

}
