package br.com.asoncsts.multi.gymtrack.data.user.api

interface ExerciseExecutionApi {

    class Impl(
        private val host: String
    ) : ExerciseExecutionApi {

        override fun exerciseExecution(
            id: String
        ) = "$host/user/exercise-executions/$id"

        override fun exerciseExecutionsGet(
            ids: List<String>
        ) = "$host/user/exercise-executions?ids=${ids.joinToString(",")}"

        override fun exerciseExecutionsPost() = "$host/user/exercise-executions"

    }

    fun exerciseExecution(
        id: String
    ): String

    fun exerciseExecutionsGet(
        ids: List<String>
    ): String

    fun exerciseExecutionsPost(): String

}
