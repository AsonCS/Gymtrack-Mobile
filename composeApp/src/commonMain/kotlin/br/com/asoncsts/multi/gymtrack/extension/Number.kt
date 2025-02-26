package br.com.asoncsts.multi.gymtrack.extension

fun Int?.orEmpty() = this
    ?.toString()
    ?: ""

fun Double?.orEmpty() = this
    ?.takeIf { it > 0 }
    ?.toString()
    ?: ""

fun String.toIntValidating(
    defaultValue: Int? = null
): Int? = takeIf { it.isNotBlank() }
    ?.let { notBlank ->
        notBlank.toIntOrNull()
            ?: defaultValue
                ?.takeIf { it > 0 }
    }

fun String.toDoubleValidating(
    defaultValue: Double? = null
): Double? = takeIf { it.isNotBlank() }
    ?.takeIf { it != "0.0" }
    ?.let { notBlank ->
        notBlank.toDoubleOrNull()
            ?: defaultValue
                ?.takeIf { it > 0 }
    }
