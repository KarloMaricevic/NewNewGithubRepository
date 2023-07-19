package com.example.githubapp.feature.login.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.viewmodel.BaseViewModel
import com.example.githubapp.feature.login.presentation.model.LoginEvent
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnLoginClicked
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnPasswordInputChanged
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnUsernameInputChanged
import com.example.githubapp.feature.login.presentation.model.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel<LoginEvent>() {

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState = _viewState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LoginViewState(),
    )

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is OnUsernameInputChanged -> _viewState.update { viewState ->
                viewState.copy(
                    username = event.input
                )
            }
            is OnPasswordInputChanged -> _viewState.update { viewState ->
                viewState.copy(
                    password = event.input
                )
            }
            OnLoginClicked -> {  /*TODO*/
            }
        }
    }
}
