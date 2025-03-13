package br.com.asoncsts.multi.gymtrack.model.exercise

import br.com.asoncsts.multi.gymtrack.extension.getTimeSeconds

data class Execution constructor(
    val children: List<Execution>,
    val id: String,
    val idParent: String?,
    val notes: String?,
    val order: Int,
    val reps: Int,
    val updated: Long,
    val weight: Double
) {
    constructor(
        children: List<Execution> = emptyList(),
        id: String? = null,
        idParent: String? = null,
        notes: String? = null,
        order: Int? = null,
        reps: Int? = null,
        updated: Long? = null,
        weight: Double? = null
    ) : this(
        children = children,
        id = id
            ?: "",
        idParent = idParent,
        notes = notes,
        order = order
            ?: 0,
        reps = reps
            ?: 0,
        updated = updated
            ?: getTimeSeconds(),
        weight = weight
            ?: 0.0
    )
}
