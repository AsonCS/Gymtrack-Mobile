package br.com.asoncsts.multi.gymtrack.data.exercise.api

interface ExerciseApi {

    class Impl(
        private val host: String
    ) : ExerciseApi {

        override fun exercise(
            idOrAlias: String
        ) = "$host/exercises/$idOrAlias"

        override fun exercises() = "$host/exercises"

    }

    fun exercise(
        idOrAlias: String
    ): String

    fun exercises(): String

}
