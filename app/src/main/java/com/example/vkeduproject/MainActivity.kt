package com.example.vkeduproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.vkeduproject.ui.theme.VkEduProjectTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeduproject.presentation.appdetails.AppDetailsHeader
import com.example.vkeduproject.presentation.appdetails.AppListScreen
import com.example.vkeduproject.presentation.appdetails.mockAppsList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkEduProjectTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "app_list"
    ) {
        composable("app_list") {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate("app_details/$appId")
                }
            )
        }
        composable("app_details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")
            val selectedApp = mockAppsList.find { it.id == appId }
            if (selectedApp != null) {
                AppDetailsHeader(
                    appDetails = selectedApp
                )
            } else {
            }
        }
    }
}