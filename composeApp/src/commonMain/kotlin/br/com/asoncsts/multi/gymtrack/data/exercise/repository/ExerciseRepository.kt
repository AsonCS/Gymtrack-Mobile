package br.com.asoncsts.multi.gymtrack.data.exercise.repository

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.remote.ExerciseRemote
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

interface ExerciseRepository {

    class Impl(
        private val remote: ExerciseRemote
    ) : ExerciseRepository {

        override suspend fun getExercise(
            idOrAlias: String
        ): Wrapper<Exercise.Detail> {
            return try {
                Wrapper.Success(
                    remote.getExercise(idOrAlias)
                )
            } catch (t: Throwable) {
                //TAG_DATA.error("ExerciseRepository", t)
                Wrapper.Error(t)
            }
        }

        override suspend fun getExercises(): Wrapper<List<Exercise>> {
            return try {
                Wrapper.Success(
                    remote.getExercises()
                )
            } catch (t: Throwable) {
                //TAG_DATA.error("ExerciseRepository", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun getExercise(
        idOrAlias: String
    ): Wrapper<Exercise.Detail>

    suspend fun getExercises(): Wrapper<List<Exercise>>

}
