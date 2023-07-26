package com.example.githubapp.feature.login.data.datasource.remote.models

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticateResponse (
    @JsonProperty("access_token") val token: String,
    @JsonProperty("scope") val scope: String,
    @JsonProperty("token_type") val tokenType: String,
)
