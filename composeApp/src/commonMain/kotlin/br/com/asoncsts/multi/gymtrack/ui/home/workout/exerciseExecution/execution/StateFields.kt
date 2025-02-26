package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import br.com.asoncsts.multi.gymtrack.extension.*

internal data class StateFields(
    private val _notes: String? = null,
    private val _reps: Int? = null,
    private val _weight: Double? = null,
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
}
