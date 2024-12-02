package br.com.asoncsts.multi.gymtrack

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepositoryMock
import br.com.asoncsts.multi.gymtrack.ui._app.App
import org.koin.dsl.module

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gymtrack",
    ) {
        App(
            platformModule = module {
                single<AuthRepository> {
                    AuthRepositoryMock
                }
            }
        )
    }
}
