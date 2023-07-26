package com.example.githubapp.feature.login.domain.usecase

import android.net.Uri
import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.core.right
import com.example.githubapp.core.result.Failure
import com.example.githubapp.core.result.Failure.ErrorMessage
import javax.inject.Inject

class ParseLoginRedirectUrlToCode @Inject constructor() {

    companion object {

        private const val CODE_PARAMETER_ID = "code"
        private const val STATE_PARAMETER_ID = "state"
    }

    fun invoke(
        redirectUrl: String,
        state: String,
    ): Either<Failure, String> = either {
        val redirectSecurityCode =
            Uri.parse(redirectUrl).getQueryParameters(CODE_PARAMETER_ID).first()
        this.ensure(redirectSecurityCode != state) { ErrorMessage("Security code mismatch") }
        val code = Uri.parse(redirectUrl).getQueryParameters(STATE_PARAMETER_ID)
        this.ensure(code.isNotEmpty()) { ErrorMessage("No code parameter") }
        return code.first().right()
    }
}
