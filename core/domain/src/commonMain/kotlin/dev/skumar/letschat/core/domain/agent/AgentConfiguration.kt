package dev.skumar.letschat.core.domain.agent

import kotlinx.serialization.Serializable


@Serializable
data class AgentConfiguration(
    val apiKey: String,
    val systemPrompt: String
)
