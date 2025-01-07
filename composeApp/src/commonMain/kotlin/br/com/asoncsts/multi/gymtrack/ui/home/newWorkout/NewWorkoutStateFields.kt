package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

data class NewWorkoutStateFields(
    val description: String = "",
    val name: String = "",
    val update: (
        NewWorkoutStateFields.() -> NewWorkoutStateFields
    ) -> Unit
) {
    fun updateDescription(
        description: String
    ) {
        update {
            copy(
                description = description
            )
        }
    }

    fun updateName(
        name: String
    ) {
        update {
            copy(
                name = name
            )
        }
    }
}
