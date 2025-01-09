package br.com.asoncsts.multi.gymtrack.model.exercise

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
            exercise: Exercise?,
            name: String
        ) : this(
            description = description,
            exercise = exercise,
            id = "",
            name = name,
            executions = emptyList()
        )
    }
}
