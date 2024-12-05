package br.com.asoncsts.multi.gymtrack.model

interface UserExercise {
    val exercise: Exercise
    val id: String
    val name: String

    data class Impl(
        override val exercise: Exercise,
        override val id: String,
        override val name: String
    ) : UserExercise

    data class Detail(
        override val exercise: Exercise,
        override val id: String,
        override val name: String,
        val description: String?,
        val executions: List<Execution>
    ) : UserExercise
}