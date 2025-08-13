package dev.skumar.letschat.core.domain.utils


class ValidationException(override val message: String): Exception()

class AgentNotInitializedException(override val message: String): Exception()