package br.com.asoncsts.multi.gymtrack.extension

actual fun platformError(
    message: String,
    tag: String,
    throwable: Throwable?
) {
    println("$tag: $message - ${throwable?.message}")
    throwable?.printStackTrace()
}

actual fun platformLog(
    message: String,
    tag: String
) {
    println("$tag: $message")
}
