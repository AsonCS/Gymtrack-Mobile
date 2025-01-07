package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

data class NewWorkoutStateFields(
    val description: String? = null,
    val name: String? = null,
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
                    .takeIf { it.isNotBlank() }
            )
        }
    }

    fun updateName(
        name: String
    ) {
        update {
            copy(
                name = name
                    .takeIf { it.isNotBlank() }
            )
        }
    }

}
