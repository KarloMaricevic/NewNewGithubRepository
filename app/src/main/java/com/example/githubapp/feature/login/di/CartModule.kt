package com.example.githubapp.feature.login.di

import com.example.githubapp.feature.login.data.repository.AuthRepositoryImpl
import com.example.githubapp.feature.login.domain.models.GithubLoginLink
import com.example.githubapp.feature.login.domain.repository.AuthRepository
import com.example.githubapp.feature.login.domain.usecase.Authenticate
import com.example.githubapp.feature.login.domain.usecase.AuthenticateImpl
import com.example.githubapp.feature.login.domain.usecase.CreateGithubLoginLink
import com.example.githubapp.feature.login.domain.usecase.CreateGithubLoginLinkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LoginModule {

    @Binds
    fun bindsAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsAuthenticate(authenticateImpl: AuthenticateImpl): Authenticate

    @Binds
    fun bindsCrateGithubLoginLink(gitHubLoginLinkImpl: CreateGithubLoginLinkImpl): CreateGithubLoginLink
}
