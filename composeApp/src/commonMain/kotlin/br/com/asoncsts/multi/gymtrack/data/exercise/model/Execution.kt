package br.com.asoncsts.multi.gymtrack.data.exercise.model

data class Execution(
    val exercise: Exercise,
    val id: String,
    val notes: String,
    val reps: Int,
    val weight: Int
)
