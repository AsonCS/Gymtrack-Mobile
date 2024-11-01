package br.com.asoncsts.multi.gymtrack.ui.auth.login

import androidx.compose.ui.graphics.painter.Painter

internal class LoginProps(
    val googleLogin: String,
    val login: String,
    val logo: Painter,
    val onFinish: () -> Unit,
    val onGoogleLogin: () -> Unit,
    val onSignup: () -> Unit,
    val onSuccess: () -> Unit,
    val onUpdatePassword: (String) -> Unit,
    val onUpdateUsername: (String) -> Unit,
    val password: String,
    val passwordPlaceholder: String,
    val signup: String,
    val userName: String,
    val userNamePlaceholder: String
)
