package br.com.asoncsts.multi.gymtrack.data.auth

sealed class AuthException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception() {
    data object InvalidDisplayNameException : AuthException("InvalidDisplayNameException")
    data object InvalidPasswordException : AuthException("InvalidPasswordException")
    data object InvalidPhotoUrlException : AuthException("InvalidPhotoUrlException")
    data object InvalidUserException : AuthException("InvalidUserException")
    data object InvalidUserNameException : AuthException("InvalidUserNameException")

    class FirebaseAuthInvalidCredentialsException(
        cause: Throwable
    ) : AuthException(
        "FirebaseAuthInvalidCredentialsException",
        cause
    )

    class FirebaseAuthWeakPasswordException(
        cause: Throwable
    ) : AuthException(
        "FirebaseAuthWeakPasswordException",
        cause
    )

    class GetCredentialCancellationException(
        cause: Throwable
    ) : AuthException(
        "GetCredentialCancellationException",
        cause
    )

    class GetCredentialException(
        cause: Throwable
    ) : AuthException(
        "GetCredentialException",
        cause
    )

    class NoCredentialException(
        cause: Throwable
    ) : AuthException(
        "NoCredentialException",
        cause
    )

    data object NotImplementedYetException : AuthException("NotImplementedYetException")

    class UnknownException(
        cause: Throwable? = null
    ) : AuthException(
        "UnknownException",
        cause
    )
}
