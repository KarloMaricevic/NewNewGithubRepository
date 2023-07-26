package com.example.githubapp.feature.login.domain.usecase

import android.net.Uri
import com.example.githubapp.core.utils.GIT_HUB_CLIENT_ID
import com.example.githubapp.core.utils.RandomGenerator
import com.example.githubapp.feature.login.domain.models.GithubLoginLink
import javax.inject.Inject

// TODO figure out what to do about Android dependency
class CreateGithubLoginLinkImpl @Inject constructor(
    private val randomGenerator: RandomGenerator,
) : CreateGithubLoginLink {

    companion object {

        private const val LOGIN_BASE_URL = """https://github.com/login/oauth/authorize"""
        private const val SECURE_KEY_SIZE = 16
        private const val CLIENT_ID_PARAMETER = "client_id"
        private const val STATE_PARAMETER = "state"
        private const val SCOPE_PARAMETER = "scope"
        private const val MIN_SCOPE = "read:user,public_repo,gist"
    }

    override operator fun invoke(): GithubLoginLink {
        val securityKey = randomGenerator.getString(SECURE_KEY_SIZE)
        return GithubLoginLink(
            securityKey,
            createLink(securityKey)
        )
    }

    private fun createLink(securityKey: String) =
        Uri.parse(LOGIN_BASE_URL)
            .buildUpon()
            .appendQueryParameter(SCOPE_PARAMETER, MIN_SCOPE)
            .appendQueryParameter(CLIENT_ID_PARAMETER, GIT_HUB_CLIENT_ID)
            .appendQueryParameter(STATE_PARAMETER, randomGenerator.getString(SECURE_KEY_SIZE))
            .build()
            .toString()
}
