package com.example.githubapp.feature.login.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.right
import com.example.githubapp.core.result.Failure
import com.example.githubapp.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateImpl @Inject constructor(
    private val repository: AuthRepository,
) : Authenticate {

    override suspend operator fun invoke(code: String): Either<Failure, Boolean> = either {
        return repository.getToken(code).right()
    }
}
