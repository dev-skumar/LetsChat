package dev.skumar.letschat.service.agent

import ai.koog.agents.core.agent.AIAgent
import dev.skumar.letschat.core.domain.agent.AgentConfiguration
import dev.skumar.letschat.core.domain.agent.AgentController
import dev.skumar.letschat.core.domain.utils.AgentNotInitializedException


class AgentControllerImpl(): AgentController {

    private lateinit var agent: AIAgent<String, String>


    override suspend fun initializeAgent(agentConfiguration: AgentConfiguration) {
        agent = AgentFactory.create(agentConfiguration)
    }


    override suspend fun closeAgent() {
        agent.close()
    }


    override suspend fun processTextInput(input: String): String {
        if (this::agent.isInitialized) {
            return agent.run(input)
        } else {
            throw AgentNotInitializedException("AI Agent is not properly initialized!")
        }
    }

}