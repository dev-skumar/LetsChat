package dev.skumar.letschat.service.agent

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.core.agent.singleRunStrategy
import ai.koog.agents.core.tools.ToolRegistry
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor
import dev.skumar.letschat.core.domain.agent.AgentConfiguration


class AgentFactory() {
    companion object {

        fun create(agentConfiguration: AgentConfiguration): AIAgent<String, String> {
            return AIAgent(
                executor = simpleGoogleAIExecutor(agentConfiguration.apiKey),
                strategy = singleRunStrategy(),
                llmModel = GoogleModels.Gemini2_0FlashLite,
                systemPrompt = agentConfiguration.systemPrompt.ifEmpty {
                    """
                        You are a friendly and helpful chatbot.
                        Always greet the user warmly, answer questions clearly, and provide accurate, concise information.
                        Use simple, easy-to-understand language.
                        If you don’t know something, politely say so instead of guessing.
                        Stay positive, respectful, and supportive in all interactions.
                    """.trimIndent()
                },
                toolRegistry = toolRegistry,
            )
        }

        private val toolRegistry = ToolRegistry {
//            tool(SayToUser)
//            tool(AskUser)
//            tool(ExitTool)
        }

    }
}