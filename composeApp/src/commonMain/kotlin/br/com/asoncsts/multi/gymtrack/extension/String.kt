@file:OptIn(ExperimentalUuidApi::class)

package br.com.asoncsts.multi.gymtrack.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import br.com.asoncsts.multi.gymtrack.isDebug
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

expect fun platformError(
    message: String,
    tag: String,
    throwable: Throwable? = null
)

expect fun platformLog(
    message: String,
    tag: String
)

fun String.error(
    message: String,
    throwable: Throwable? = null
) {
    if (isDebug) {
        platformError(message, this, throwable)
    }
}

fun String.log(
    message: String
) {
    if (isDebug) {
        platformLog(message, this)
    }
}

@Composable
fun String.capitalized(
    locale: Locale = Locale.current
) = this.capitalize(locale)

@Composable
fun String.capitalizedWords(
    locale: Locale = Locale.current
) = this.split(" ")
    .joinToString(" ") {
        it.capitalize(locale)
    }

fun String?.orUuidHexString(): String = this
    ?.takeIf { it.isNotBlank() }
    ?: Uuid.random().toHexString()
