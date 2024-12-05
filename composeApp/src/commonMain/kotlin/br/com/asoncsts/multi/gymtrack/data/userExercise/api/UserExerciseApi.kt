package br.com.asoncsts.multi.gymtrack.data.userExercise.api

interface UserExerciseApi {

    class Impl(
        private val host: String
    ) : UserExerciseApi {

        override fun userExercise(
            id: String
        ) = "$host/user-exercises/$id"

        override fun userExercises() = "$host/user-exercises"

    }

    fun userExercise(
        id: String
    ): String

    fun userExercises(): String

}
