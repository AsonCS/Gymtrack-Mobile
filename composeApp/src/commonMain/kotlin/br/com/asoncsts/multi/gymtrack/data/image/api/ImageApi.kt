package br.com.asoncsts.multi.gymtrack.data.image.api

interface ImageApi {

    class Impl(
        private val host: String
    ) : ImageApi {
        override fun image(
            name: String,
            path: String
        ) = "$host/$path/$name"
    }

    fun image(
        name: String,
        path: String
    ): String

}
