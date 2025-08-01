package dev.skumar.letschat.core.domain.error


data class ErrorDialog(
    val title: String,
    val message: String,
    val extraInfo: String,
    val enableRetry: Boolean,
    val onRetry: () -> Unit,
    val enableOkay: Boolean,
    val onOkay: () -> Unit
)