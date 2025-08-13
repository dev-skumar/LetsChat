package dev.skumar.letschat.feature.chat.domain.usecases

import dev.skumar.letschat.core.domain.agent.AgentController


class CloseAgent(
    private val agentController: AgentController
) {
    suspend operator fun invoke() {
        agentController.closeAgent()
    }
}