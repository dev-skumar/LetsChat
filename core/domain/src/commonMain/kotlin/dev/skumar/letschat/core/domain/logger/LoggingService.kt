package dev.skumar.letschat.core.domain.logger


interface LoggingService {

    fun logInfo(tag: String, message: String)

    fun logDebug(tag: String, message: String)

    fun logError(tag: String, message: String, throwable: Throwable? = null)

}