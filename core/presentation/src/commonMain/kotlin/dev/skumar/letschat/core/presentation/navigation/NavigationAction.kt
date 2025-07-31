package dev.skumar.letschat.core.presentation.navigation


sealed class NavigationAction {

    data class NavigateTo(
        val screen: Screen
    ) : NavigationAction()


    data object NavigateUp : NavigationAction()

}