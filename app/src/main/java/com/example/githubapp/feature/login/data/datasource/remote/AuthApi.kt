package com.example.githubapp.feature.login.data.datasource.remote

import com.example.githubapp.core.utils.GIT_BUB_CLIENT_SECRET
import com.example.githubapp.core.utils.GIT_HUB_CLIENT_ID
import com.example.githubapp.feature.login.data.datasource.remote.models.AuthenticateResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST
    suspend fun authenticateCode(
        @Query("code") code: String,
        @Query("client_id") clientId: String = GIT_HUB_CLIENT_ID,
        @Query("client_secret") clientSecret: String = GIT_BUB_CLIENT_SECRET,
    ): AuthenticateResponse
}