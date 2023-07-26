package com.example.githubapp.core.webScreen.router

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.githubapp.core.navigation.NavigationDestination
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val WEB_ROUTER_URL_PARAM = "url"
private const val WEB_SCREEN_ROOT = "webScreen"

private const val WEB_SCREEN_ROUTE =
    "$WEB_SCREEN_ROOT/{$WEB_ROUTER_URL_PARAM}"
object WebScreenRouter : NavigationDestination {
    override fun route() = WEB_SCREEN_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(WEB_ROUTER_URL_PARAM) { type = NavType.StringType },
        )

    fun createWebScreenRoute(url: String) = "$WEB_SCREEN_ROOT/${URLEncoder.encode(url, StandardCharsets.UTF_8.toString())}"
}
