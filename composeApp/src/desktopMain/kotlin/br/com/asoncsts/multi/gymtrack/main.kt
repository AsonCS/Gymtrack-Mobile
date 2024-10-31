package br.com.asoncsts.multi.gymtrack

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepositoryMock
import br.com.asoncsts.multi.gymtrack.ui._app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gymtrack",
    ) {
        App(
            AuthRepositoryMock
        )
    }
}
