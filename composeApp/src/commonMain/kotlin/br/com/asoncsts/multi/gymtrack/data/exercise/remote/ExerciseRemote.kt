package br.com.asoncsts.multi.gymtrack.data.exercise.remote

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

interface ExerciseRemote {

    class Impl(
        private val api: ExerciseApi
    ) : ExerciseRemote {

        override suspend fun getExercise(
            idOrAlias: String
        ): Exercise.Detail {
            val result = api.exercise(idOrAlias)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                else -> result.data
                    .toExerciseDetail()
            }
        }

        override suspend fun getExercises(): List<Exercise> {
            val result = api.exercises()

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toExercise() }
            }
        }

    }

    suspend fun getExercise(
        idOrAlias: String
    ): Exercise.Detail

    suspend fun getExercises(): List<Exercise>

}
