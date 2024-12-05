package br.com.asoncsts.multi.gymtrack.data.userExercise.remote

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.userExercise.api.UserExerciseApi
import br.com.asoncsts.multi.gymtrack.data.userExercise.remote.model.UserExerciseSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface UserExerciseRemote {

    class Impl(
        private val api: UserExerciseApi,
        private val client: HttpClient
    ) : UserExerciseRemote {

        override suspend fun getUserExercise(
            id: String
        ): Response<UserExerciseSource> {
            return client.get {
                url {
                    takeFrom(api.userExercise(id))
                }
            }.body()
        }

        override suspend fun getUserExercises(): Response<List<UserExerciseSource>> {
            return client.get {
                url {
                    takeFrom(api.userExercises())
                }
            }.body()
        }

    }

    suspend fun getUserExercise(
        id: String
    ): Response<UserExerciseSource>

    suspend fun getUserExercises(): Response<List<UserExerciseSource>>

}
