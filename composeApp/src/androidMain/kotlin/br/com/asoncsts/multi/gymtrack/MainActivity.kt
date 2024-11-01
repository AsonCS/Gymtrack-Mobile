package br.com.asoncsts.multi.gymtrack

import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.asoncsts.multi.gymtrack.di.androidModule
import br.com.asoncsts.multi.gymtrack.ui._app.App

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            App(
                platformModule = androidModule(this)
            )
        }
    }
}
