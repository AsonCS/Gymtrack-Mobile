package br.com.asoncsts.multi.gymtrack._mock.data.exercise

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise

object ExerciseMock {
    val exerciseDetails = listOf(
        "Remada maquina 30".let {
            Exercise.Detail(
                alias = it,
                description = it,
                image = null,
                title = it,
                video = null
            )
        },
        "Remada Alta".let {
            Exercise.Detail(
                alias = it,
                description = it,
                image = null,
                title = it,
                video = null
            )
        },
        "Biceps Halter".let {
            Exercise.Detail(
                alias = it,
                description = it,
                image = null,
                title = it,
                video = null
            )
        },
    )

    val exercises: List<Exercise>
        get() = exerciseDetails
}
