package br.com.asoncsts.multi.gymtrack.ui.exerciseExecution

import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

internal data class StateFields(
    private val _name: String? = null,
    private val _description: String? = null,
    val id: String? = null,
    val isDialogVisible: Boolean = false,
    var update: (
        StateFields.() -> StateFields
    ) -> Unit
) {

    var name: String
        get() = _name.orEmpty()
        set(value) {
            update {
                copy(
                    _name = value
                        .takeIf { it.isNotBlank() }
                )
            }
        }

    var description: String
        get() = _description.orEmpty()
        set(value) {
            update {
                copy(
                    _description = value
                        .takeIf { it.isNotBlank() }
                )
            }
        }

    fun onClose() {
        update {
            copy(
                _name = null,
                _description = null,
                id = null,
                isDialogVisible = false
            )
        }
    }

    fun onCreate() {
        update {
            copy(
                _name = null,
                _description = null,
                id = null,
                isDialogVisible = true
            )
        }
    }

    fun onEdit(
        exerciseExecution: ExerciseExecution.Detail
    ) {
        update {
            copy(
                _name = exerciseExecution.name,
                _description = exerciseExecution.description,
                id = exerciseExecution.id,
                isDialogVisible = true
            )
        }
    }

    fun toExerciseExecution(): ExerciseExecution.Detail {
        val exerciseExecution = ExerciseExecution.Detail(
            description = _description,
            id = id,
            name = _name
                ?: throw IllegalStateException("Name cannot be empty")
        )
        onClose()
        return exerciseExecution
    }

}
