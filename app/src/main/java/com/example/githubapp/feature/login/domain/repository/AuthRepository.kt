package com.example.githubapp.feature.login.domain.repository

import arrow.core.Either
import com.example.githubapp.core.result.Failure

interface AuthRepository {

    fun getToken(
        code: String,
    ): Either<Failure, Boolean>
}
