package dev.skumar.letschat.core.domain.utils

import dev.skumar.letschat.core.domain.error.ErrorInfo


sealed interface Result<out D> {

    data class Success<out D>(val data: D): Result<D>

    data class Failure(val error: ErrorInfo): Result<Nothing>

    data object Loading : Result<Nothing>
}
