package br.com.asoncsts.multi.gymtrack.extension

import br.com.asoncsts.multi.gymtrack.external.console

actual fun platformError(
    message: String,
    tag: String,
    throwable: Throwable?
) {
    console.error(
        tag,
        message,
        throwable?.message.toString()
    )
}

actual fun platformLog(
    message: String,
    tag: String
) {
    console.log(tag, message)
}
