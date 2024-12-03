package br.com.asoncsts.multi.gymtrack.di

import io.ktor.client.engine.js.Js

actual val platform = object : Platform {
    override val engine = Js
}
