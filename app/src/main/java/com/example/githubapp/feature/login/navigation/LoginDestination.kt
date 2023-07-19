package com.example.githubapp.feature.login.navigation

import com.example.githubapp.core.navigation.NavigationDestination


object LoginDestination : NavigationDestination {

    override fun route(): String = LOGIN_ROUTE

    private const val LOGIN_ROUTE = "login"
}
