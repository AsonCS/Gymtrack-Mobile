package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

data class NewExerciseExecutionStateFields(
    val description: String? = null,
    val exercise: Exercise? = null,
    val name: String? = null,
    val update: (
        NewExerciseExecutionStateFields.() -> NewExerciseExecutionStateFields
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

    fun updateExercise(
        exercise: Exercise
    ) {
        update {
            copy(
                exercise = exercise
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
