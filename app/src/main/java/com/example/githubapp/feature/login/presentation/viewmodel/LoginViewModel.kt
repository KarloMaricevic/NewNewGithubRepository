package com.example.githubapp.feature.login.presentation.viewmodel

import com.example.githubapp.core.viewmodel.BaseViewModel
import com.example.githubapp.feature.login.presentation.model.LoginEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(): BaseViewModel<LoginEvent>() {

    override fun onEvent(event: LoginEvent) {
        TODO("Not yet implemented")
    }
}
