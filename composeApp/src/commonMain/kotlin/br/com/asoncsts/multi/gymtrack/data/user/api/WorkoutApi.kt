package br.com.asoncsts.multi.gymtrack.data.user.api
/*

import br.com.asoncsts.multi.gymtrack.data._utils.Response
import br.com.asoncsts.multi.gymtrack.data.user.remote.model.WorkoutSource
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface WorkoutApi {

    class Impl(
        private val client: HttpClient,
        private val host: String
    ) : WorkoutApi {

        override suspend fun getWorkouts() = client
            .get("$host/user/workouts")
            .body<Response<List<WorkoutSource>>>()

        override suspend fun putWorkout(
            workout: Workout
        ) = client
            .put("$host/user/workouts") {
                contentType(ContentType.Application.Json)
                setBody(
                    WorkoutSource(
                        workout
                    )
                )
            }.body<Response<WorkoutSource>>()

    }

    suspend fun getWorkouts(): Response<List<WorkoutSource>>

    suspend fun putWorkout(
        workout: Workout
    ): Response<WorkoutSource>

}
*/
