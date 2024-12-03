package br.com.asoncsts.multi.gymtrack.data._utils

sealed class Wrapper<T> {
    class Success<T>(
        val data: T
    ) : Wrapper<T>()

    class Error<T>(
        val error: Throwable
    ) : Wrapper<T>()
}
