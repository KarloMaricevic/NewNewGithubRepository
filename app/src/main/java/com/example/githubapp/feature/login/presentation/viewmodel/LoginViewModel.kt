package com.example.githubapp.feature.login.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.extensions.context
import com.example.githubapp.core.extensions.openWebPage
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.viewmodel.BaseViewModel
import com.example.githubapp.feature.login.domain.usecase.Authenticate
import com.example.githubapp.feature.login.domain.usecase.CreateGithubLoginLink
import com.example.githubapp.feature.login.domain.usecase.ParseLoginRedirectUrlToCode
import com.example.githubapp.feature.login.presentation.model.LoginEvent
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnInit
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnLoginClicked
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnPasswordInputChanged
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnUsernameInputChanged
import com.example.githubapp.feature.login.presentation.model.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticate: Authenticate,
    private val createGitHubLoginLink: CreateGithubLoginLink,
    private val navigator: Navigator,
    private val parseRedirectLink: ParseLoginRedirectUrlToCode,
    application: Application,
) : BaseViewModel<LoginEvent>(application) {

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState = _viewState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LoginViewState(),
    )
    private var githubLinkSecurityKey: String? = null

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is OnInit -> handleInitEvent(event.redirectUrl)
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

            OnLoginClicked -> handleUserLoginClick()
        }
    }

    private fun handleUserLoginClick() {
        val githubLink = createGitHubLoginLink()
        githubLinkSecurityKey = githubLink.securityKey
        context.openWebPage(githubLink.link)
    }

    private fun handleInitEvent(redirectUrl: String?) {
        if (redirectUrl != null) {
            parseRedirectLink.invoke(
                redirectUrl,
                githubLinkSecurityKey ?: ""
            )

        }
    }
}
