package com.example.githubapp.core.webScreen.router

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.githubapp.core.navigation.NavigationDestination

const val WEB_ROUTER_URL_ID = "url"

object WebScreenRouter : NavigationDestination {

    private const val WEB_SCREEN_ROUTE = "webScreen"

    override fun route() = WEB_SCREEN_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(WEB_ROUTER_URL_ID) { type = NavType.StringType },
        )

    fun createWebScreenRoute(url: String) = "$WEB_ROUTER_URL_ID/$url"
}
