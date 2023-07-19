package com.example.githubapp.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.githubapp.feature.login.presentation.LoginScreen

const val LOGIN_ROOT = "loginRoot"

fun NavGraphBuilder.buildLoginGraph() {
    navigation(
        startDestination = LoginDestination.route(),
        route = LOGIN_ROOT,
    ) {
        composable(LoginDestination.route()) {
            LoginScreen()
        }
    }
}
