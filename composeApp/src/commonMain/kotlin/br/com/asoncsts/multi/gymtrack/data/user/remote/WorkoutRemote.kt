package br.com.asoncsts.multi.gymtrack.data.user.remote

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.user.api.WorkoutApi
import br.com.asoncsts.multi.gymtrack.data.user.remote.model.WorkoutSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface WorkoutRemote {

    class Impl(
        private val api: WorkoutApi,
        private val client: HttpClient
    ) : WorkoutRemote {

        override suspend fun getWorkouts(): Response<List<WorkoutSource>> {
            return client.get {
                url {
                    takeFrom(api.workouts())
                }
            }.body()
        }

    }

    suspend fun getWorkouts(): Response<List<WorkoutSource>>

}
