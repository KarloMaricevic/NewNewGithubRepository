package com.example.githubapp.feature.login.data.repository

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.right
import com.example.githubapp.core.result.Failure
import com.example.githubapp.feature.login.data.datasource.remote.AuthApi
import com.example.githubapp.feature.login.data.datasource.remote.models.AuthenticateResponse
import com.example.githubapp.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
) : AuthRepository {

    override fun getToken(code: String): Either<Failure, AuthenticateResponse> = either {

        return api.authenticateCode(code).right()
    }
}
