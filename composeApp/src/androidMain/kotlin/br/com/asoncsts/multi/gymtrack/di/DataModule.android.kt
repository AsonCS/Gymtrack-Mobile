package br.com.asoncsts.multi.gymtrack.di

import io.ktor.client.engine.okhttp.OkHttp

actual val platform = object : Platform {
    override val engine = OkHttp
}
