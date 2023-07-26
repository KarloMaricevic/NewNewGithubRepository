package com.example.githubapp.feature.login.domain.usecase

import com.example.githubapp.feature.login.domain.models.GithubLoginLink

interface CreateGithubLoginLink {

    operator fun invoke(): GithubLoginLink
}
