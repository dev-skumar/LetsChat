package dev.skumar.letschat.service.logger

import co.touchlab.kermit.Logger
import dev.skumar.letschat.core.domain.logger.LoggingService


class LoggingServiceImpl(
    private val logger: Logger
): LoggingService {

    override fun logInfo(tag: String, message: String) {
        logger.i(tag = tag) { message }
    }


    override fun logDebug(tag: String, message: String) {
        logger.d(tag = tag) { message }
    }


    override fun logError(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        logger.e(tag = tag, throwable = throwable) { message }
    }

}