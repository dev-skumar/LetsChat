package dev.skumar.letschat.feature.chat.domain.usecases

import dev.skumar.letschat.core.domain.agent.AgentController
import dev.skumar.letschat.core.domain.error.ErrorInfo
import dev.skumar.letschat.core.domain.error.ErrorMessage
import dev.skumar.letschat.core.domain.error.ErrorTitle
import dev.skumar.letschat.core.domain.utils.AgentNotInitializedException
import dev.skumar.letschat.core.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProcessTextInput(
    private val agentController: AgentController
) {
    operator fun invoke(input: String): Flow<Result<String>> = flow {
        try {
            emit(Result.Loading)
            val output = agentController.processTextInput(input)
            emit(Result.Success(output))
        } catch (e: Exception) {

            val errorInfo = when(e) {

                is AgentNotInitializedException -> {
                    ErrorInfo(
                        title = ErrorTitle.AGENT_ERROR,
                        message = e.message
                    )
                }

                else -> {
                    ErrorInfo(
                        title = ErrorTitle.APPLICATION_ERROR,
                        message = e.message ?: ErrorMessage.NOT_PROVIDE
                    )
                }
            }

            emit(Result.Failure(errorInfo))
        }
    }
}