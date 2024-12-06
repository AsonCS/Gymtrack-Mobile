package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.remote.ExerciseExecutionRemote
import br.com.asoncsts.multi.gymtrack.extension.*
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExerciseExecutionRepository {

    class Impl(
        private val remote: ExerciseExecutionRemote,
        private val lang: () -> DeviceLanguage = ::deviceLanguage
    ) : ExerciseExecutionRepository {

        override suspend fun getExerciseExecution(
            id: String
        ): Wrapper<ExerciseExecution.Detail> {
            return try {
                val result = remote.getExerciseExecution(id)
                when {
                    result.data != null -> {
                        return Wrapper.Success(
                            result.data.toExerciseExecutionDetail(
                                lang()
                            )
                        )
                    }

                    result.error != null -> {
                        return Wrapper.Error(
                            Throwable(result.error)
                        )
                    }

                    else -> {
                        Wrapper.Error(
                            UnknownException()
                        )
                    }
                }
            } catch (t: Throwable) {
                TAG_DATA.error("UserExerciseRepository", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }

        override suspend fun getExerciseExecutions(
            ids: List<String>
        ): Wrapper<List<ExerciseExecution>> {
            return try {
                val result = remote.getExerciseExecutions(ids)
                when {
                    !result.data.isNullOrEmpty() -> {
                        Wrapper.Success(
                            result.data.map {
                                it.toExerciseExecution(
                                    lang()
                                )
                            }
                        )
                    }

                    result.data != null -> {
                        Wrapper.Error(
                            EmptyException()
                        )
                    }

                    result.error != null -> {
                        Wrapper.Error(
                            Throwable(result.error)
                        )
                    }

                    else -> {
                        Wrapper.Error(
                            UnknownException()
                        )
                    }
                }
            } catch (t: Throwable) {
                TAG_DATA.error("UserExerciseRepository", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }

    }

    suspend fun getExerciseExecution(
        id: String
    ): Wrapper<ExerciseExecution.Detail>

    suspend fun getExerciseExecutions(
        ids: List<String>
    ): Wrapper<List<ExerciseExecution>>

}
