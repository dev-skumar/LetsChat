package dev.skumar.letschat

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.skumar.letschat.app.App
import dev.skumar.letschat.di.initKoin
import kotlinx.browser.document


@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    initKoin()

    ComposeViewport(document.body!!) {
        App()
    }

}