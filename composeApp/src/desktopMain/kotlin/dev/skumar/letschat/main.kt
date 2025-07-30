package dev.skumar.letschat

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.skumar.letschat.app.App
import dev.skumar.letschat.di.initKoin


fun main() {

    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "LetsChat",
        ) {
            App()
        }
    }

}