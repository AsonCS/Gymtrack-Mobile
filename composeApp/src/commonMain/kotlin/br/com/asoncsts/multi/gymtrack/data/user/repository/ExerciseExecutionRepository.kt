package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.remote.ExerciseExecutionRemote
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExerciseExecutionRepository {

    class Impl(
        private val remote: ExerciseExecutionRemote
    ) : ExerciseExecutionRepository {

        override suspend fun getExerciseExecution(
            id: String
        ): Wrapper<ExerciseExecution.Detail> {
            return try {
                Wrapper.Success(
                    remote.getExerciseExecution(id)
                )
            } catch (t: Throwable) {
                //TAG_DATA.error("UserExerciseRepository", t)
                Wrapper.Error(t)
            }
        }

        override suspend fun getExerciseExecutions(): Wrapper<List<ExerciseExecution.SimpleView>> {
            return try {
                Wrapper.Success(
                    remote.getExerciseExecutions()
                )
            } catch (t: Throwable) {
                //TAG_DATA.error("UserExerciseRepository.getExerciseExecutions", t)
                //Wrapper.Error(t)
                Wrapper.Success(listOf())
            }
        }

        override suspend fun getExerciseExecutions(
            ids: List<String>
        ): Wrapper<List<ExerciseExecution>> {
            return try {
                Wrapper.Success(
                    remote.getExerciseExecutionsPost(ids)
                )
            } catch (t: Throwable) {
                //TAG_DATA.error("UserExerciseRepository", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun getExerciseExecution(
        id: String
    ): Wrapper<ExerciseExecution.Detail>

    suspend fun getExerciseExecutions(): Wrapper<List<ExerciseExecution.SimpleView>>

    suspend fun getExerciseExecutions(
        ids: List<String>
    ): Wrapper<List<ExerciseExecution>>

}
