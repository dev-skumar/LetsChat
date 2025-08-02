package dev.skumar.letschat.core.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Screen {

    @Serializable
    data object Onboarding: Screen

    @Serializable
    data object Home: Screen

    @Serializable
    data object Settings: Screen

}