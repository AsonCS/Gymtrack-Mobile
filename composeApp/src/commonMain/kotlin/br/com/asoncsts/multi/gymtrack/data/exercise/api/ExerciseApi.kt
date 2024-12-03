package br.com.asoncsts.multi.gymtrack.data.exercise.api

interface ExerciseApi {

    class Impl(
        private val host: String
    ) : ExerciseApi {

        override fun exercise(
            id: String
        ) = "$host/exercises/$id"

        override fun exercises() = "$host/exercises"

    }

    fun exercise(
        id: String
    ): String

    fun exercises(): String

}
