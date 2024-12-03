package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import kotlinx.coroutines.flow.*

abstract class AppViewModel : ViewModel() {

    class Impl : AppViewModel() {

        private val _stateAuth = MutableStateFlow<AuthState>(
            AuthState.Unknown
        )
        override val stateAuth = _stateAuth
            .asStateFlow()

        private val _stateTopBar = MutableStateFlow(
            AppTopBarState()
        )
        override val stateTopBar = _stateTopBar
            .asStateFlow()

        override fun stateAuthUpdate(
            state: AuthState
        ) {
            _stateAuth.update {
                state
            }
        }

        override fun stateTopBarUpdate(
            handlerBack: (() -> Unit)?,
            showUser: Boolean
        ) {
            _stateTopBar.update {
                AppTopBarState(
                    handlerBack = handlerBack,
                    showUser = showUser
                )
            }
        }

    }

    abstract val stateAuth: StateFlow<AuthState>

    abstract val stateTopBar: StateFlow<AppTopBarState>

    abstract fun stateAuthUpdate(
        state: AuthState
    )

    abstract fun stateTopBarUpdate(
        handlerBack: (() -> Unit)? = null,
        showUser: Boolean = false
    )

}
