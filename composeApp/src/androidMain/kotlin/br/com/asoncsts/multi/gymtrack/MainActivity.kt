package br.com.asoncsts.multi.gymtrack

import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity()
/*AndroidAuthRepository*/ {

//    override val activity get() = this
//    override var emit = { _: AuthState -> }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)

        setContent {
//            App(
//                auth = this,
//                platformModule = module {
//                    factory<Context> { this@MainActivity }
//                }
//            )
        }
    }
}
