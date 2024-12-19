package br.com.asoncsts.multi.gymtrack.data._exceptions

class UnknownException(
    cause: Throwable?,
    message: String
) : Exception(
    message,
    cause
) {
    constructor(
        message: String? = null
    ) : this(
        cause = null,
        message = message ?: "Unknown Exception"
    )

    constructor(
        cause: Throwable?
    ) : this(
        cause = cause,
        message = "Unknown Exception"
    )
}
