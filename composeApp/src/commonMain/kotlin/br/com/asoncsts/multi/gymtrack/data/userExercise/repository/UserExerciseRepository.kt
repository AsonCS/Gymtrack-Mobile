package br.com.asoncsts.multi.gymtrack.data.userExercise.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.userExercise.remote.UserExerciseRemote
import br.com.asoncsts.multi.gymtrack.extension.*
import br.com.asoncsts.multi.gymtrack.model.UserExercise

interface UserExerciseRepository {

    class Impl(
        private val remote: UserExerciseRemote,
        private val lang: () -> DeviceLanguage = ::deviceLanguage
    ) : UserExerciseRepository {

        override suspend fun getUserExercise(
            id: String
        ): Wrapper<UserExercise.Detail> {
            return try {
                val result = remote.getUserExercise(id)
                when {
                    result.data != null -> {
                        return Wrapper.Success(
                            result.data.toUserExerciseDetail(
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

        override suspend fun getUserExercises(): Wrapper<List<UserExercise>> {
            return try {
                val result = remote.getUserExercises()
                when {
                    !result.data.isNullOrEmpty() -> {
                        Wrapper.Success(
                            result.data.map {
                                it.toUserExercise(
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

    suspend fun getUserExercise(
        id: String
    ): Wrapper<UserExercise.Detail>

    suspend fun getUserExercises(): Wrapper<List<UserExercise>>

}
