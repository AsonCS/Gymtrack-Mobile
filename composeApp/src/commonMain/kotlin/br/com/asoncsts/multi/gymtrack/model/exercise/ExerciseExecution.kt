package br.com.asoncsts.multi.gymtrack.model.exercise

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper

interface ExerciseExecution {
    val exercise: Exercise?
    val id: String
    val name: String

    data class Impl(
        override val exercise: Exercise?,
        override val id: String,
        override val name: String,
        val exerciseAlias: String?
    ) : ExerciseExecution {

        constructor(
            exerciseAlias: String?,
            id: String,
            name: String
        ) : this(
            exercise = null,
            exerciseAlias = exerciseAlias,
            id = id,
            name = name
        )

        constructor(
            exercise: Exercise?,
            id: String,
            name: String
        ) : this(
            exercise = exercise,
            exerciseAlias = exercise
                ?.alias,
            id = id,
            name = name
        )

    }

    data class Detail(
        override val exercise: Exercise?,
        override val id: String,
        override val name: String,
        val description: String?,
        val executions: List<Execution>
    ) : ExerciseExecution {
        constructor(
            description: String?,
            name: String,
            exercise: Exercise? = null,
            id: String? = null
        ) : this(
            description = description,
            exercise = exercise,
            id = id
                ?: "",
            name = name,
            executions = emptyList()
        )
    }

    companion object {

        fun <E : ExerciseExecution> E.fillExercise(
            getExercise: (alias: String) -> Wrapper<Exercise>,
        ): E {
            val exercise = exercise
                ?.alias
                ?.let { alias ->
                    getExercise(alias)
                }?.let {
                    when (it) {
                        is Wrapper.Success -> {
                            it.data
                        }

                        is Wrapper.Error -> {
                            null
                        }
                    }
                }

            @Suppress("UNCHECKED_CAST")
            return when (this) {
                is Impl -> {
                    copy(
                        exercise = exercise
                    )
                }

                is Detail -> {
                    copy(
                        exercise = exercise
                    )
                }

                else -> this
            } as E
        }

        fun <E : ExerciseExecution> List<E>.fillExercise(
            getExercise: (alias: String) -> Wrapper<Exercise>,
            onEach: (E) -> Unit = {}
        ): List<E> {
            return map {
                val result = it.fillExercise(getExercise)
                onEach(result)
                result
            }
        }

    }
}
