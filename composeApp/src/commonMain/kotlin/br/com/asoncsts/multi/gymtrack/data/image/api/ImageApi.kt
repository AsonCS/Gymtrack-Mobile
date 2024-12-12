package br.com.asoncsts.multi.gymtrack.data.image.api

interface ImageApi {

    class Impl(
        private val host: String
    ) : ImageApi {
        override fun image(
            alias: String,
            image: String
        ) = "$host/$alias%2F$image"
    }

    fun image(
        alias: String,
        image: String
    ): String

}
