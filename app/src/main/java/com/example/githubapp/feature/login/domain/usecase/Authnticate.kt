package com.example.githubapp.feature.login.domain.usecase

import arrow.core.Either
import com.example.githubapp.core.result.Failure

interface Authenticate {

    suspend operator fun invoke(code: String): Either<Failure, Boolean>
}
