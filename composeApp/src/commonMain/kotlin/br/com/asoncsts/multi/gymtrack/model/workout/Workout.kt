package br.com.asoncsts.multi.gymtrack.model.workout

data class Workout(
    val description: String?,
    val exerciseExecutionIds: List<String>,
    val id: String,
    val name: String
) {

    constructor(
        description: String?,
        exerciseExecutionIds: List<String>,
        name: String
    ) : this(
        description = description,
        exerciseExecutionIds = exerciseExecutionIds,
        id = "",
        name = name
    )

    val amount: String
        get() = exerciseExecutionIds.size.toString()

}
