package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.WorkoutDestination.Args

data object WorkoutDestination : HomeNavDestination<Args>(
    "workout"
) {
    class Args(
        val workout: Workout
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            Text(
                args.workout.toString()
            )
            Here
            //WorkoutScreen(args)
        }
    }
}
