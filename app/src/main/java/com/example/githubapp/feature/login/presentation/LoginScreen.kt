package com.example.githubapp.feature.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.R
import com.example.githubapp.core.components.PrimaryButton
import com.example.githubapp.core.components.TertiaryButton
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnLoginClicked
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnPasswordInputChanged
import com.example.githubapp.feature.login.presentation.model.LoginEvent.OnUsernameInputChanged
import com.example.githubapp.feature.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.default_background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_github_logo),
            contentDescription = stringResource(R.string.default_content_description),
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 60.dp),
        )
        OutlinedTextField(
            value = viewState.username,
            onValueChange = { input -> (viewModel::onEvent)(OnUsernameInputChanged(input)) },
            label = { Text(text = stringResource(R.string.login_screen_username_hint)) },
        )
        OutlinedTextField(
            value = viewState.password,
            onValueChange = { input -> (viewModel::onEvent)(OnPasswordInputChanged(input)) },
            label = { Text(text = stringResource(R.string.login_screen_login_hint)) },
            modifier = Modifier.padding(top = 16.dp),
        )
        PrimaryButton(
            text = stringResource(R.string.login_screen_login_button),
            onClick = { (viewModel::onEvent)(OnLoginClicked) },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(top = 40.dp)
                .fillMaxWidth(),
        )
        TertiaryButton(
            text = stringResource(R.string.login_screen_continue_as_guest_button),
            onClick = {},
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
