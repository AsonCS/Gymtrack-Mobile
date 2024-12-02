package br.com.asoncsts.multi.gymtrack.ui.auth.signup

import androidx.compose.ui.graphics.painter.Painter
import br.com.asoncsts.multi.gymtrack.ui.auth.Props

internal class SignupProps(
    val logo: Painter,
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
