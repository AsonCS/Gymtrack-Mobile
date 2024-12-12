package br.com.asoncsts.multi.gymtrack.model.workout

data class Workout(
    val description: String?,
    val exerciseExecutionIds: List<String>,
    val id: String,
    val name: String
) {
    val amount: String
        get() = exerciseExecutionIds.size.toString()
}
