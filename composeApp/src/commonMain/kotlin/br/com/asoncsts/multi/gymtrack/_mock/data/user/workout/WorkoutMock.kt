package br.com.asoncsts.multi.gymtrack._mock.data.user.workout

import br.com.asoncsts.multi.gymtrack._mock.data.user.exerciseExecution.ExerciseExecutionMock
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

object WorkoutMock {
    val workoutDetails = listOf(
        Workout(
            description = "O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um espécime de livro. Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.",
            id = "id_monday_workout_0",
            name = "monday workout 0",
            exerciseExecutions = ExerciseExecutionMock.exerciseExecutions
        ),
        Workout(
            description = "O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um espécime de livro. Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.",
            id = "id_tuesday_workout_1",
            name = "tuesday workout 1",
            exerciseExecutions = ExerciseExecutionMock.exerciseExecutions
        ),
    )

    val workouts: List<Workout>
        get() = workoutDetails
}
