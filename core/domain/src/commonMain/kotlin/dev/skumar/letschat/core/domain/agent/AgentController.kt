package dev.skumar.letschat.core.domain.agent


interface AgentController {

    suspend fun initializeAgent(agentConfiguration: AgentConfiguration)

    suspend fun closeAgent()

    suspend fun processTextInput(input: String): String

}