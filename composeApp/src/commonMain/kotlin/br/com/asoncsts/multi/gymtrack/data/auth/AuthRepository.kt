package br.com.asoncsts.multi.gymtrack.data.auth

import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import br.com.asoncsts.multi.gymtrack.data.auth.model.User

interface AuthRepository {

    suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    )

    suspend fun login(
        password: String,
        username: String
    )

    suspend fun loginWithGoogle()

    suspend fun logout()

    suspend fun lookupAndEmit()

    suspend fun signup(
        password: String,
        username: String
    )

    suspend fun update(
        displayName: String,
        photoUrl: String
    ): User

    fun verifyDisplayName(
        displayName: String?
    ): String {
        if (displayName.isNullOrBlank())
            throw AuthException.InvalidDisplayNameException

        return displayName
    }

    fun verifyPassword(
        password: String
    ): String {
        return password.trim().let {
            val isValid =
                it == "123456" || Regex(
                    "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"
                ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidPasswordException
        }
    }

    fun verifyPhotoUrl(
        photoUrl: String?
    ): String {
        if (photoUrl.isNullOrBlank())
            throw AuthException.InvalidPhotoUrlException

        photoUrl.trim().let {
            val isValid = Regex(
                "(http(s?):)([/|.\\w\\s-])*\\.(?:jpg|gif|png)"
            ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidPhotoUrlException
        }

        return photoUrl
    }

    fun verifyUsername(
        username: String
    ): String {
        return username.trim().let {
            val isValid = Regex(
                "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
            ).matches(it)
            if (isValid)
                it
            else
                throw AuthException.InvalidUserNameException
        }
    }

}
