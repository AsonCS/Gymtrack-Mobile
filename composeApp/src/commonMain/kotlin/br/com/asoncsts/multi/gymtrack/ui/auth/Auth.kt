package br.com.asoncsts.multi.gymtrack.ui.auth

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.ui._app.TAG_APP
import kotlinx.coroutines.flow.StateFlow

const val TAG_AUTH = "${TAG_APP}auth"

abstract class AuthViewModel : ViewModel() {
    internal abstract val state: StateFlow<LoginState>

    internal abstract fun login()

    internal abstract fun loginWithGoogle()

    internal abstract fun signup()

    internal abstract fun updatePassword(
        password: String
    )

    internal abstract fun updateUsername(
        username: String
    )
}
