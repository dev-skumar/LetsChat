package dev.skumar.letschat.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.skumar.letschat.core.presentation.theme.LetsChatTheme
import dev.skumar.letschat.navigation.AppNavigation

@Composable
fun App(
    modifier: Modifier = Modifier
) {
    LetsChatTheme {
        AppNavigation(modifier)
    }
}