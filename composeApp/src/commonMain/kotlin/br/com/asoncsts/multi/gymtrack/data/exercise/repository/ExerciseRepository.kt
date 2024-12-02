package br.com.asoncsts.multi.gymtrack.data.exercise.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.extension.error

interface ExerciseRepository {

    class Impl(
        private val remote: ExerciseRemote
    ) : ExerciseRepository {
        override suspend fun getExercises(): Wrapper<List<Exercise>> {
            return try {
                val result = remote.getExercises()
                return when {
                    !result.data.isNullOrEmpty() -> {
                        Wrapper.Success(
                            result.data.map {
                                it.toExercise()
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
            } catch (t: EmptyException) {
                Wrapper.Error(t)
            } catch (t: Throwable) {
                TAG_DATA.error("ExerciseRepository.Other", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }
    }

    suspend fun getExercises(): Wrapper<List<Exercise>>

}
