package br.com.asoncsts.multi.gymtrack.ui.auth.login

import androidx.compose.ui.graphics.painter.Painter
import br.com.asoncsts.multi.gymtrack.ui.auth.Props

internal class LoginProps(
    val googleLogin: String,
    val login: String,
    val logo: Painter,
    val onGoogleLogin: () -> Unit,
    val onSignup: () -> Unit,
    val onSuccess: () -> Unit,
    val signup: String,
    override val onFinish: () -> Unit,
    override val onUpdatePassword: (String) -> Unit,
    override val onUpdateUsername: (String) -> Unit,
    override val password: String,
    override val passwordPlaceholder: String,
    override val userName: String,
    override val userNamePlaceholder: String
) : Props
