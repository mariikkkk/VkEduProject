package com.example.vkeduproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeduproject.ui.theme.VkEduProjectTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeduproject.data.appdetails.AppDetailsApi
import com.example.vkeduproject.data.appdetails.AppDetailsMapper
import com.example.vkeduproject.data.appdetails.AppDetailsMockRepositoryImpl
import com.example.vkeduproject.data.appdetails.CategoryMapper
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.GetAppDetailsUseCase
import com.example.vkeduproject.presentation.appdetails.AppDetailsHeader
import com.example.vkeduproject.presentation.appdetails.AppDetailsScreen
import com.example.vkeduproject.presentation.applist.AppListScreen
import com.example.vkeduproject.presentation.applist.AppListViewModel
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
    val categoryMapper = CategoryMapper()
    val appDetailsMapper = AppDetailsMapper(categoryMapper)
    val getAppDetailsUseCase = GetAppDetailsUseCase(
        repository = AppDetailsMockRepositoryImpl(
            mapper = appDetailsMapper,
            api = AppDetailsApi()
        )
    )
    NavHost(
        navController = navController,
        startDestination = "app_list"
    ) {
        composable("app_list") {
            val viewModel: AppListViewModel = viewModel()
            AppListScreen(
                viewModel = viewModel,
                onAppClick = { appId ->
                    navController.navigate("app_details/$appId")
                }
            )
        }
        composable("app_details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: ""
            var selectedApp by remember { mutableStateOf<AppDetails?>(null) }
            LaunchedEffect(appId) {
                selectedApp = getAppDetailsUseCase(appId)

            }
            if (selectedApp != null) {
                AppDetailsScreen(
                    appId,
                    viewModel = viewModel(),
                    onBackClick = { navController.popBackStack() }
                )
            } else {
            }
        }
    }
}