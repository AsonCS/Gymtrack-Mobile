package br.com.asoncsts.multi.gymtrack.extension

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import br.com.asoncsts.multi.gymtrack.isDebug

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

fun String.capitalizedWords(
    locale: Locale
) = this.split(" ")
    .joinToString(" ") {
        it.capitalize(locale)
    }
