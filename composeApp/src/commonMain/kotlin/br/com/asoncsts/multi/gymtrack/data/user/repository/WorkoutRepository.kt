package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.remote.WorkoutRemote
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutRepository {

    class Impl(
        private val remote: WorkoutRemote
    ) : WorkoutRepository {

        override suspend fun getWorkouts(): Wrapper<List<Workout>> {
            return try {
                val result = remote.getWorkouts()
                when {
                    !result.data.isNullOrEmpty() -> {
                        Wrapper.Success(
                            result.data.map {
                                it.toWorkout()
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
                TAG_DATA.error("WorkoutRepository", t)
                Wrapper.Error(
                    UnknownException(t)
                )
            }
        }
    }

    suspend fun getWorkouts(): Wrapper<List<Workout>>

}
