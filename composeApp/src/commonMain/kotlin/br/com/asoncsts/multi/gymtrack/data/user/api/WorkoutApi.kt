package br.com.asoncsts.multi.gymtrack.data.user.api

interface WorkoutApi {

    class Impl(
        private val host: String
    ) : WorkoutApi {

        override fun workouts() = "$host/user/workouts"

    }

    fun workouts(): String

}
