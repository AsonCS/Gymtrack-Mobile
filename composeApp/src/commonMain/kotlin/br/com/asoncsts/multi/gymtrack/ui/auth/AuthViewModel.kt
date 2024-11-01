package br.com.asoncsts.multi.gymtrack.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.ui.auth.login.LoginState
import br.com.asoncsts.multi.gymtrack.ui.auth.login.LoginState.Filling
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class AuthViewModel : ViewModel() {

    class Impl(
        private val auth: AuthRepository
    ) : AuthViewModel() {

        private val _state = MutableStateFlow<LoginState>(Filling())
        override val state = _state.asStateFlow()

        override fun login() {
            viewModelScope.launch {
                emitLoading()
                runCatching {
                    val username = auth.verifyUsername(
                        _state.value.username
                    )
                    val password = auth.verifyPassword(
                        _state.value.password
                    )
                    auth.login(
                        password = password,
                        username = username
                    )
                    auth.lookupAndEmit()
                }.onFailure { error ->
                    emitFilling(
                        error.message
                            ?: "login"
                    )
                    TAG_AUTH.error("login", error)
                }.onSuccess {
                    emitSuccess()
                }
            }
        }

        override fun loginWithGoogle() {
            viewModelScope.launch {
                emitLoading()
                runCatching {
                    auth.loginWithGoogle()
                }.onFailure { error ->
                    emitFilling(
                        error.message
                            ?: "loginWithGoogle"
                    )
                    TAG_AUTH.error("loginWithGoogle", error)
                }.onSuccess {
                    emitSuccess()
                }
            }
        }

        override fun signup() {
            viewModelScope.launch {
                emitLoading()
                runCatching {
                    val username = auth.verifyUsername(
                        _state.value.username
                    )
                    val password = auth.verifyPassword(
                        _state.value.password
                    )
                    auth.signup(
                        password = password,
                        username = username
                    )
                }.onFailure { error ->
                    emitFilling(
                        error.message
                            ?: "signup"
                    )
                    TAG_AUTH.error("signup", error)
                }.onSuccess {
                    emitSuccess()
                }
            }
        }

        override fun updatePassword(
            password: String
        ) {
            _state.update {
                it.toFilling(
                    password = password
                )
            }
        }

        override fun updateUsername(
            username: String
        ) {
            _state.update {
                it.toFilling(
                    username = username
                )
            }
        }

        private fun emitFilling(
            errorMessage: String
        ) {
            _state.update {
                Filling(
                    errorMessage = errorMessage,
                    username = it.username,
                    password = it.password
                )
            }
        }

        private fun emitLoading() {
            _state.update {
                LoginState.Loading(
                    password = it.password,
                    username = it.username
                )
            }
        }

        private fun emitSuccess() {
            _state.update {
                LoginState.Success
            }
        }

    }

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
