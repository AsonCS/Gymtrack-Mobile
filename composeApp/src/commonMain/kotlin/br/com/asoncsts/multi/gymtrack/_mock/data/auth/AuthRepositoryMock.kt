package br.com.asoncsts.multi.gymtrack._mock.data.auth

import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedOut
import br.com.asoncsts.multi.gymtrack.data.auth.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import org.koin.core.component.KoinComponent

object AuthRepositoryMock : AuthRepository, KoinComponent {

    init {
        CoroutineScope(Default).launch {
            delay(3_000)
            emit(LoggedOut)
        }
    }

    var mockUser = User(
        "AsonCS Mock",
        "asoncs_github_mock@mock.com.br",
        "https://avatars.githubusercontent.com/u/42609750?v=4",
        "uid"
    )

    var emit = { _: AuthState -> }

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        AuthRepositoryMock.emit = emit
        emit(LoggedOut)
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

    override suspend fun loginWithGoogle() {
        delay(1_500)
        emit(
            LoggedIn(mockUser)
        )
    }

    override suspend fun logout() {
        emit(LoggedOut)
    }

    override suspend fun lookupAndEmit() {
        emit(
            LoggedIn(mockUser)
        )
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        loginWithGoogle()
    }

    override suspend fun update(
        displayName: String,
        photoUrl: String
    ): User {
        mockUser = mockUser.copy(
            displayName = displayName,
            photoUrl = photoUrl
        )

        return mockUser
    }

}
