package br.com.asoncsts.multi.gymtrack.model.exercise

data class Execution(
    val id: String,
    val notes: String?,
    val reps: Int,
    val weight: Double
) {
    constructor(
        id: String?,
        notes: String?,
        reps: Int?,
        weight: Double?
    ) : this(
        id = id
            ?: "",
        notes = notes,
        reps = reps
            ?: 0,
        weight = weight
            ?: 0.0
    )
}
