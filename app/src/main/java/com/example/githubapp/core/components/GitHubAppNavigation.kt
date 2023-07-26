package com.example.githubapp.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.githubapp.core.navigation.NavigationEvent.Destination
import com.example.githubapp.core.navigation.NavigationEvent.NavigateBack
import com.example.githubapp.core.navigation.NavigationEvent.NavigateUp
import com.example.githubapp.core.navigation.NavigationEvent.OpenExternalDestination
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.webScreen.WebScreen
import com.example.githubapp.core.webScreen.router.WEB_ROUTER_URL_PARAM
import com.example.githubapp.core.webScreen.router.WebScreenRouter
import com.example.githubapp.feature.login.navigation.LOGIN_ROOT
import com.example.githubapp.feature.login.navigation.LoginDestination
import com.example.githubapp.feature.login.navigation.REDIRECT_CALLBACK_ID
import com.example.githubapp.feature.login.presentation.LoginScreen
import timber.log.Timber
import timber.log.Timber.Forest
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun GitHubAppNavigation(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        navigator.navigationEvent.collect { navigationEvent ->
            when (navigationEvent) {
                NavigateUp -> navController.navigateUp()
                NavigateBack -> navController.popBackStack()
                is Destination -> navController.navigate(
                    route = navigationEvent.destination,
                    builder = navigationEvent.builder,
                )

                is OpenExternalDestination -> context.startActivity(navigationEvent.intent)
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROOT,
    ) {
        navigation(
            startDestination = LoginDestination.route(),
            route = LOGIN_ROOT,
        ) {
            composable(
                LoginDestination.route(),
                deepLinks = LoginDestination.deepLinks,
                arguments = LoginDestination.arguments
            ) { backStackEntry ->
                LoginScreen(
                    redirectUrl = backStackEntry.arguments?.getString(REDIRECT_CALLBACK_ID)
                )
            }
        }
        composable(
            route = WebScreenRouter.route(),
            arguments = WebScreenRouter.arguments,
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString(WEB_ROUTER_URL_PARAM)
                ?: error("$WEB_ROUTER_URL_PARAM was not provided to web screen route")
            WebScreen(URLDecoder.decode(url, StandardCharsets.UTF_8.toString()))
        }
    }
}
