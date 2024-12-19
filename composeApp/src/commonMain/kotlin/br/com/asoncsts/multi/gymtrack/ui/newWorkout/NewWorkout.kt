package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.ui._navigation.NewWorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.components.NewWorkoutScreen
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewWorkoutScreen(
    args: Args,
    modifier: Modifier = Modifier,
    viewModel: NewWorkoutViewModel = koinViewModel()
) {
    val state by viewModel
        .state
        .collectAsState()

    NewWorkoutScreen(
        state,
        modifier
    )
}

abstract class NewWorkoutViewModel : ViewModel() {
    internal abstract val state: StateFlow<NewWorkoutState>
}
