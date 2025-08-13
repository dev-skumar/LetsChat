package dev.skumar.letschat.feature.chat.presentation.home


sealed class HomeEvent {

    data class UpdatePromptField(val newValue: String): HomeEvent()

    data object SendTextInput: HomeEvent()

}