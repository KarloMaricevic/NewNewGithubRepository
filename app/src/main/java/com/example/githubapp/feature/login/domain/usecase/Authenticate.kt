package com.example.githubapp.feature.login.domain.usecase

import com.example.githubapp.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class Authenticate @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = repository.login(
        username = username,
        password = password,
    )
}
