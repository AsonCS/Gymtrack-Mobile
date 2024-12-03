package br.com.asoncsts.multi.gymtrack.data._exceptions

class UnknownException(
    cause: Throwable? = null
) : Exception(
    "Unknown Exception",
    cause
)
