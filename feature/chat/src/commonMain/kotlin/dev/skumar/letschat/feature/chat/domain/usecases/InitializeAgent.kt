package dev.skumar.letschat.feature.chat.domain.usecases

import dev.skumar.letschat.core.domain.agent.AgentConfiguration
import dev.skumar.letschat.core.domain.agent.AgentController
import dev.skumar.letschat.core.domain.error.ErrorInfo
import dev.skumar.letschat.core.domain.error.ErrorMessage
import dev.skumar.letschat.core.domain.error.ErrorTitle
import dev.skumar.letschat.core.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InitializeAgent(
    private val agentController: AgentController
) {
    operator fun invoke(agentConfiguration: AgentConfiguration): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            agentController.initializeAgent(agentConfiguration)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            val errorInfo = ErrorInfo(
                title = ErrorTitle.APPLICATION_ERROR,
                message = e.message ?: ErrorMessage.NOT_PROVIDE
            )
            emit(Result.Failure(errorInfo))
        }
    }
}