package br.com.asoncsts.multi.gymtrack.data.user.api

interface ExerciseExecutionApi {

    class Impl(
        private val host: String
    ) : ExerciseExecutionApi {

        override fun exerciseExecution(
            id: String
        ) = "$host/user/exercise-executions/$id"

        override fun exerciseExecutions(
            ids: List<String>
        ) = "$host/user/exercise-executions?ids=${ids.joinToString(",")}"

    }

    fun exerciseExecution(
        id: String
    ): String

    fun exerciseExecutions(
        ids: List<String>
    ): String

}
