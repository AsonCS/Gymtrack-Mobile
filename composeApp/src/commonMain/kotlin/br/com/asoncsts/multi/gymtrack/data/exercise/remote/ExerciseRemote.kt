package br.com.asoncsts.multi.gymtrack.data.exercise.remote

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data.exercise.api.ExerciseApi
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage
import br.com.asoncsts.multi.gymtrack.extension.deviceLanguage
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

interface ExerciseRemote {

    class Impl(
        private val api: ExerciseApi,
        private val lang: () -> DeviceLanguage = ::deviceLanguage
    ) : ExerciseRemote {

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExercise(
            idOrAlias: String
        ): Exercise.Detail {
            TODO("Not yet implemented")
            val result = api.exercise(idOrAlias)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                else -> result.data
                    .toExerciseDetail(lang())
            }
        }

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExercises(): List<Exercise> {
            TODO("Not yet implemented")
            val result = api.exercises()

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toExercise(lang()) }
            }
        }

    }

    suspend fun getExercise(
        idOrAlias: String
    ): Exercise.Detail

    suspend fun getExercises(): List<Exercise>

}
