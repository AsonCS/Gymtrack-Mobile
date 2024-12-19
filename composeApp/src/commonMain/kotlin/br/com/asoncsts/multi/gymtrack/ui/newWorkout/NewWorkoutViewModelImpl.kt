package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewWorkoutViewModelImpl : NewWorkoutViewModel() {

    private val _state = MutableStateFlow(Loading)
    override val state = _state.asStateFlow()

}
