package com.example.githubapp.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubapp.core.navigation.NavigationEvent.Destination
import com.example.githubapp.core.navigation.NavigationEvent.NavigateBack
import com.example.githubapp.core.navigation.NavigationEvent.NavigateUp
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.webScreen.WebScreen
import com.example.githubapp.core.webScreen.router.WEB_ROUTER_URL_ID
import com.example.githubapp.core.webScreen.router.WebScreenRouter
import com.example.githubapp.feature.login.navigation.LOGIN_ROOT
import com.example.githubapp.feature.login.navigation.buildLoginGraph

@Composable
fun GitHubAppNavigation(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffect(key1 = Unit) {
        navigator.navigationEvent.collect { navigationEvent ->
            when (navigationEvent) {
                NavigateUp -> navController.navigateUp()
                NavigateBack -> navController.popBackStack()
                is Destination -> navController.navigate(
                    route = navigationEvent.destination,
                    builder = navigationEvent.builder,
                )
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROOT,
    ) {
        buildLoginGraph()
        composable(
            route = WebScreenRouter.route(),
            arguments = WebScreenRouter.arguments,
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString(WEB_ROUTER_URL_ID)
                ?: error("$WEB_ROUTER_URL_ID was not provided to web screen route")
            WebScreen(url)
        }
    }
}
