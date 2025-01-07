package br.com.asoncsts.multi.gymtrack.model.exercise

interface Exercise {
    val alias: String
    val image: String?
    val title: String

    data class Impl(
        override val alias: String,
        override val image: String? = null,
        override val title: String = ""
    ) : Exercise

    data class Detail(
        override val alias: String,
        override val image: String?,
        override val title: String,
        val description: String,
        val video: String?
    ) : Exercise
}
