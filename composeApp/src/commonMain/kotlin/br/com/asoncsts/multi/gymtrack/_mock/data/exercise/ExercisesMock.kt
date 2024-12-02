package br.com.asoncsts.multi.gymtrack._mock.data.exercise

import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise

object ExercisesMock {
    val exercises = listOf(
        "Remada maquina 30".let {
            Exercise(
                description = it,
                id = it,
                image = null,
                title = it
            )
        },
        "Remada Alta".let {
            Exercise(
                description = it,
                id = it,
                image = null,
                title = it
            )
        },
        "Biceps Halter".let {
            Exercise(
                description = it,
                id = it,
                image = null,
                title = it
            )
        },
    )
}
