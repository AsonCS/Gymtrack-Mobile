package br.com.asoncsts.multi.gymtrack.ui.workout

import br.com.asoncsts.multi.gymtrack.model.workout.Workout

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
        workout: Workout
    ) {
        update {
            copy(
                _name = workout.name,
                _description = workout.description,
                id = workout.id,
                isDialogVisible = true
            )
        }
    }

    fun toWorkout(): Workout {
        val workout = Workout(
            description = _description,
            id = id
                ?: "",
            exerciseExecutionIds = emptyList(),
            name = _name
                ?: throw IllegalStateException("Name cannot be empty")
        )
        onClose()
        return workout
    }

}
