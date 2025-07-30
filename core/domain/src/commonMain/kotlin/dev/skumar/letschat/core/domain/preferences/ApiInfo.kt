package dev.skumar.letschat.core.domain.preferences

import kotlinx.serialization.Serializable


@Serializable
data class ApiInfo(
    val key: String
)