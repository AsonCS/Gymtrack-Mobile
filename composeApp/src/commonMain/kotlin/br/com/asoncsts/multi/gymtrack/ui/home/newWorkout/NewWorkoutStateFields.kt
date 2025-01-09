package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

data class NewWorkoutStateFields(
    val description: String? = null,
    val exerciseExecution: ExerciseExecution? = null,
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

    fun updateExerciseExecution(
        exerciseExecution: ExerciseExecution
    ) {
        update {
            copy(
                exerciseExecution = exerciseExecution
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
