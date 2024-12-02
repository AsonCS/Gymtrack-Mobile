package br.com.asoncsts.multi.gymtrack.data.exercise.api

interface ExerciseApi {

    class Impl(
        private val host: String
    ) : ExerciseApi {
        override fun exercises() = "$host/exercises"
    }

    fun exercises(): String

}
