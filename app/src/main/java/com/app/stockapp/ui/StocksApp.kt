package com.app.stockapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.stockapp.ui.feature.home.HomeScreen

@Composable
fun StocksApp() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        StockNavigationActions(navController)
    }
    NavHost(navController = navController, startDestination = Route.Home) {
        composable<Route.Home> {
            HomeScreen(navigationActions)
        }
    }
}
