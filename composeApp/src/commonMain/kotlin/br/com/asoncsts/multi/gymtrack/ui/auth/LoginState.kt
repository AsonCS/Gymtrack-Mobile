package br.com.asoncsts.multi.gymtrack.ui.auth

internal sealed class LoginState(
    val password: String,
    val username: String
) {
    class Filling(
        val errorMessage: String? = null,
        password: String = "",
        username: String = ""
    ) : LoginState(
        password = password,
        username = username
    )

    class Loading(
        password: String,
        username: String
    ) : LoginState(
        password = password,
        username = username
    )

    data object Success : LoginState("", "")

    fun toFilling(
        password: String = this.password,
        username: String = this.username
    ) = Filling(
        password = password,
        username = username
    )
}
