package dev.skumar.letschat.core.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Screen {

    @Serializable
    data object ApiKey: Screen

    @Serializable
    data object Home: Screen

    @Serializable
    data object Settings: Screen

}