package br.com.asoncsts.multi.gymtrack.data.user.remote

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data.user.api.ExerciseExecutionApi
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExerciseExecutionRemote {

    class Impl(
        private val api: ExerciseExecutionApi,
        private val hostImage: String
    ) : ExerciseExecutionRemote {

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExerciseExecution(
            id: String
        ): ExerciseExecution.Detail {
            TODO("Not yet implemented")
            val result = api.exerciseExecution(id)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                else -> result.data
                    .toExerciseExecutionDetail(hostImage)
            }
        }

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExerciseExecutionsGet(
            ids: List<String>
        ): List<ExerciseExecution> {
            TODO("Not yet implemented")
            val result = api.exerciseExecutionsGet(ids)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toExerciseExecution(hostImage) }
            }
        }

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExerciseExecutionsPost(
            ids: List<String>
        ): List<ExerciseExecution> {
            TODO("Not yet implemented")
            val result = api.exerciseExecutionsGet(ids)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toExerciseExecution(hostImage) }
            }
        }

        @Suppress("UNREACHABLE_CODE")
        override suspend fun getExerciseExecutions(): List<ExerciseExecution.SimpleView> {
            TODO("Not yet implemented")
            val result = api.getExerciseExecutions()

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toSimpleView() }
            }
        }
    }

    suspend fun getExerciseExecution(
        id: String
    ): ExerciseExecution.Detail

    suspend fun getExerciseExecutionsGet(
        ids: List<String>
    ): List<ExerciseExecution>

    suspend fun getExerciseExecutionsPost(
        ids: List<String>
    ): List<ExerciseExecution>

    suspend fun getExerciseExecutions(): List<ExerciseExecution.SimpleView>

}
