package br.com.asoncsts.multi.gymtrack.data.auth.model

sealed class AuthState {
    data class LoggedIn(val user: User) : AuthState()
    data object LoggedOut : AuthState()
    data object Unknown : AuthState()
}
