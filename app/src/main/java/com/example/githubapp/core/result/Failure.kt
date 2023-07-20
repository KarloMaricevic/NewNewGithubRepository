package com.example.githubapp.core.result

import com.example.githubapp.core.result.Failure.ErrorMessage
import com.example.githubapp.core.result.Failure.Unknown

sealed interface Failure {
    data class ErrorMessage(val errorMessage: String) : Failure
    object Unknown : Failure
}

fun Failure.foldToString(): String = when (this) {
    is ErrorMessage -> errorMessage
    // TODO: expand with Context receiver
    is Unknown -> "Unknown error"
}
