package com.example.githubapp.feature.login.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.githubapp.core.navigation.NavigationDestination

const val REDIRECT_CALLBACK_ID = "redirectCallbackId"

object LoginDestination : NavigationDestination {

    override fun route(): String = LOGIN_ROUTE

    private const val LOGIN_ROUTE = "login"
    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(REDIRECT_CALLBACK_ID) { type = NavType.StringType })
    override val deepLinks: List<NavDeepLink>
        get() = listOf(
            navDeepLink {
                uriPattern = "example://login{$REDIRECT_CALLBACK_ID}"
            }
        )
}