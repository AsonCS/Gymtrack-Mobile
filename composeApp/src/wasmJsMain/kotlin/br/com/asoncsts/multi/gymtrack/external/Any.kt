@file:Suppress("ClassName")

package br.com.asoncsts.multi.gymtrack.external

external object console : JsAny {
    fun error(a: JsAny)
    fun error(a: String, b: String, c: String)
    fun log(a: JsAny)
    fun log(a: String, b: JsAny?)
    fun log(a: String, b: String)
}
