package com.asix.dikshatek.components.config

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object NavConfig {
    private var navController: NavHostController? = null

    @Composable
    fun getNavController(): NavHostController {
        if (navController == null) {
            navController = rememberNavController()
        }
        return navController!!
    }
}