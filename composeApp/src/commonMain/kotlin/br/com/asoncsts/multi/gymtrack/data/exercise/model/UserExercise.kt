package br.com.asoncsts.multi.gymtrack.data.exercise.model

data class UserExercise(
    val exercise: Exercise,
    val id: String,
    val executions: List<Execution>
)
