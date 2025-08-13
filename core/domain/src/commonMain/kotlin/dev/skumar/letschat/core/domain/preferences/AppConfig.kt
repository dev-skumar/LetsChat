package dev.skumar.letschat.core.domain.preferences

import dev.skumar.letschat.core.domain.agent.AgentConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class AppConfig(
    val agentConfiguration: AgentConfiguration
) {
    companion object {
        fun defaultInitialization() = AppConfig(
            agentConfiguration = AgentConfiguration(
                apiKey = "",
                systemPrompt = ""
            )
        )
    }
}


fun AppConfig.encodeToString(): String {
    return Json.encodeToString<AppConfig>(this)
}


fun String.decodeAppConfigFromString(): AppConfig {
    return Json.decodeFromString<AppConfig>(this)
}
