package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import br.com.asoncsts.multi.gymtrack.extension.*
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution

internal data class StateFields(
    private val _notes: String? = null,
    private val _order: Int? = null,
    private val _reps: Int? = null,
    private val _weight: Double? = null,
    val id: String? = null,
    val idParent: String? = null,
    val isDialogVisible: Boolean = false,
    private val update: (
        StateFields.() -> StateFields
    ) -> Unit
) {
    var notes: String
        get() = _notes.orEmpty()
        set(value) {
            update {
                copy(
                    _notes = value
                        .takeIf { it.isNotBlank() }
                )
            }
        }

    var order: String
        get() = _order.orEmpty()
        set(value) {
            update {
                copy(
                    _order = value
                        .toIntValidating(_reps)
                )
            }
        }

    var reps: String
        get() = _reps.orEmpty()
        set(value) {
            update {
                copy(
                    _reps = value
                        .toIntValidating(_reps)
                )
            }
        }

    var weight: String
        get() = _weight.orEmpty()
        set(value) {
            update {
                copy(
                    _weight = value
                        .toDoubleValidating(_weight)
                )
            }
        }

    fun toExecution() = Execution(
        id = id,
        idParent = idParent,
        notes = _notes,
        order = _order
            ?.let { it - 1 },
        reps = _reps,
        weight = _weight
    )
}
