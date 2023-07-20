package com.example.githubapp.feature.login.domain.usecase

import arrow.core.Either
import com.example.githubapp.core.result.Failure
import com.example.githubapp.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateImpl @Inject constructor(
    private val repository: AuthRepository,
) : Authenticate {

    override suspend operator fun invoke(
        username: String,
        password: String,
    ): Either<Failure, Boolean> = repository.login(
        username = username,
        password = password,
    )
}
