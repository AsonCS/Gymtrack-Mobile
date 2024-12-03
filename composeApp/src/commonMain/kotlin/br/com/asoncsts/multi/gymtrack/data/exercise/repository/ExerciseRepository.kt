package br.com.asoncsts.multi.gymtrack.data.exercise.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.extension.*

interface ExerciseRepository {

    class Impl(
        private val remote: ExerciseRemote,
        private val lang: () -> DeviceLanguage = ::deviceLanguage
    ) : ExerciseRepository {

        override suspend fun getExercise(
            id: String
        ): Wrapper<Exercise> {
            return try {
                val result = remote.getExercise(id)
                when {
                    result.data != null -> {
                        return Wrapper.Success(
                            result.data.toExercise(
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
                TAG_DATA.error("ExerciseRepository", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }

        override suspend fun getExercises(): Wrapper<List<Exercise>> {
            return try {
                val result = remote.getExercises()
                when {
                    !result.data.isNullOrEmpty() -> {
                        Wrapper.Success(
                            result.data.map {
                                it.toExercise(
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
                TAG_DATA.error("ExerciseRepository", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }

    }

    suspend fun getExercise(
        id: String
    ): Wrapper<Exercise>

    suspend fun getExercises(): Wrapper<List<Exercise>>

}
