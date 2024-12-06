package br.com.asoncsts.multi.gymtrack.model.exercise

data class Execution(
    val id: String,
    val notes: String,
    val reps: Int,
    val weight: Int
)
