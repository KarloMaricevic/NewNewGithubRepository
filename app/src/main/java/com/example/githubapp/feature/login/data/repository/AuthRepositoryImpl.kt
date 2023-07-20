package com.example.githubapp.feature.login.data.repository

import arrow.core.right
import com.example.githubapp.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override fun login(
        username: String,
        password: String,
    ) = true.right()
}
