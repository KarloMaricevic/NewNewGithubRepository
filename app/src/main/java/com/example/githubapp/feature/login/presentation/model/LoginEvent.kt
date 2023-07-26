package com.example.githubapp.feature.login.presentation.model

sealed interface LoginEvent {

    data class OnUsernameInputChanged(val input: String) : LoginEvent

    data class OnPasswordInputChanged(val input: String) : LoginEvent

    object OnLoginClicked : LoginEvent

    data class OnInit(val redirectUrl: String?): LoginEvent
}
