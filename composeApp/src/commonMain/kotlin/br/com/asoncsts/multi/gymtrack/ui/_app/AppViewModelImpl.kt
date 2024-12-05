package br.com.asoncsts.multi.gymtrack.ui._app

import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import kotlinx.coroutines.flow.*

class AppViewModelImpl : AppViewModel() {

    private val _stateAuth = MutableStateFlow<AuthState>(
        AuthState.Unknown
    )
    override val stateAuth = _stateAuth
        .asStateFlow()

    override fun stateAuthUpdate(
        state: AuthState
    ) {
        _stateAuth.update {
            state
        }
    }

}
