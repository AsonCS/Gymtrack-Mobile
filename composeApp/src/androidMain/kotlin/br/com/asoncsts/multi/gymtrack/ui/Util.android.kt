package br.com.asoncsts.multi.gymtrack.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun getWidthDp(
    fraction: Float
): Dp {
    return (LocalConfiguration.current.screenWidthDp * fraction).dp
}

@Composable
actual fun Toast(
    text: String,
    isLong: Boolean
) {
    Toast.makeText(
        LocalContext.current,
        text,
        if (isLong)
            Toast.LENGTH_LONG
        else
            Toast.LENGTH_SHORT
    ).show()
}
