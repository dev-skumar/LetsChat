package dev.skumar.letschat.feature.settings.domain.usecases

import dev.skumar.letschat.core.domain.error.ErrorInfo
import dev.skumar.letschat.core.domain.error.ErrorMessage
import dev.skumar.letschat.core.domain.error.ErrorTitle
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import dev.skumar.letschat.core.domain.utils.Result
import dev.skumar.letschat.core.domain.utils.ValidationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UpdateAppConfig(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke(appConfig: AppConfig): Flow<Result<Nothing>> = flow {

        try {
            emit(Result.Loading)

            if (appConfig.apiInfo.key.isEmpty()) {
                throw ValidationException("Api Key Cannot be empty!")
            }

            preferencesRepository.updateAppConfig(appConfig)

        } catch (e: Exception) {

            val errorInfo = when(e) {

                is ValidationException -> {
                    ErrorInfo(
                        title = ErrorTitle.VALIDATION_ERROR,
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