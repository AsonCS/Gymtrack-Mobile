package br.com.asoncsts.multi.gymtrack.data.auth

import androidx.activity.ComponentActivity
import androidx.credentials.*
import androidx.credentials.exceptions.*
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedOut
import br.com.asoncsts.multi.gymtrack.data.auth.model.User
import br.com.asoncsts.multi.gymtrack.generated.BuildConfig
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface AndroidAuthRepository : AuthRepository {

    val activity: ComponentActivity
    var emit: (AuthState) -> Unit

    override suspend fun onAuthInit(
        emit: (AuthState) -> Unit
    ) {
        this.emit = emit
        Firebase.auth
            .currentUser
            ?.emitUser()
            ?: emit(LoggedOut)
    }

    override suspend fun login(
        password: String,
        username: String
    ) {
        try {
            Firebase.auth
                .signInWithEmailAndPassword(username, password)
                .await()
                .user
                .emitUser()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw AuthException.FirebaseAuthInvalidCredentialsException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun loginWithGoogle() {
        val auth = Firebase.auth

        val credentialManager = CredentialManager
            .create(activity)
        val googleIdOption = GetSignInWithGoogleOption
            .Builder(BuildConfig.FIREBASE_DEFAULT_WEB_CLIENT_ID)
            .build()
        val request = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                context = activity,
                request = request
            )

            val credential = result.credential

            when {
                credential is GoogleIdTokenCredential -> {
                    val authCredential = GoogleAuthProvider
                        .getCredential(
                            credential.idToken,
                            null
                        )
                    auth.signInWithCredential(authCredential)
                        .await()
                        .user
                        .emitUser()
                }

                credential is CustomCredential
                        && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                    val authCredential = GoogleAuthProvider
                        .getCredential(
                            googleIdTokenCredential.idToken,
                            null
                        )
                    auth
                        .signInWithCredential(authCredential)
                        .await()
                        .user
                        .emitUser()
                }

                else -> {
                    throw AuthException.UnknownException()
                }
            }
        } catch (e: NoCredentialException) {
            throw AuthException.NoCredentialException(e)
        } catch (e: GetCredentialCancellationException) {
            throw AuthException.GetCredentialCancellationException(e)
        } catch (e: GetCredentialException) {
            throw AuthException.GetCredentialException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun logout() {
        Firebase.auth
            .signOut()
        emit(LoggedOut)
    }

    override suspend fun lookup(): User {
        return Firebase.auth
            .currentUser
            .toUser()
    }

    override suspend fun signup(
        password: String,
        username: String
    ) {
        try {
            Firebase.auth
                .createUserWithEmailAndPassword(username, password)
                .await()
                .user
                .emitUser()
        } catch (e: FirebaseAuthWeakPasswordException) {
            throw AuthException.FirebaseAuthWeakPasswordException(e)
        } catch (t: Throwable) {
            throw AuthException.UnknownException(t)
        }
    }

    override suspend fun update(
        displayName: String,
        photoUrl: String
    ): User {
        return Firebase.auth
            .currentUser
            .toUser()
    }

    private fun FirebaseUser?.toUser(): User {
        if (this == null)
            throw AuthException.InvalidUserException

        return User(
            displayName = displayName,
            email = email,
            photoUrl = photoUrl
                ?.toString(),
            uid = uid
        )
    }

    private fun FirebaseUser?.emitUser() {
        emit(
            LoggedIn(
                toUser()
            )
        )
    }

}
