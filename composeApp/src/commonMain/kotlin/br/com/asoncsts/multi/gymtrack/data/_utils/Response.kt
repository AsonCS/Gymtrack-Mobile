package br.com.asoncsts.multi.gymtrack.data._utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    @SerialName("data")
    val data: T? = null,
    @SerialName("error")
    val error: String? = null
)
