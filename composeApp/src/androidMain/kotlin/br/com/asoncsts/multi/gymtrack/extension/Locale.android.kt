package br.com.asoncsts.multi.gymtrack.extension

import java.util.Locale

actual fun deviceLanguage() = Locale.getDefault()
    .toLanguageTag()
    .deviceLanguage
