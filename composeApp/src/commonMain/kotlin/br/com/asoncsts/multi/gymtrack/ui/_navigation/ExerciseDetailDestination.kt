package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.ExerciseDetailDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.ExerciseDetailScreen

data object ExerciseDetailDestination : AppNavDestination<Args>(
    false,
    "exercise_detail/{alias}"
) {
    class Args(
        val navigateUp: () -> Unit
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            val alias = it.arguments
                ?.getString("alias")
                ?: throw IllegalStateException("Alias is required")
            ExerciseDetailScreen(alias, args)
        }
    }

    fun route(
        alias: String
    ) = "exercise_detail/$alias"
}
