package dev.skumar.letschat.feature.chat.domain.usecases


data class ChatUseCases(
    val initializeAgent: InitializeAgent,
    val closeAgent: CloseAgent,
    val processTextInput: ProcessTextInput
)