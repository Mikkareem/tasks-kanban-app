package com.techullurgy.tasksapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techullurgy.tasksapp.presentation.ui.screens.KanbanScreen
import com.techullurgy.tasksapp.presentation.ui.screens.MyTasksScreen

val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    error("No value provided for LocalNavHostController")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    CompositionLocalProvider(value = LocalNavHostController provides navController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            modifier = Modifier.drawBehind {
                drawRect(
                    brush = Brush.linearGradient(
                        Pair(0f, Color(0xFF7871EF)),
                        Pair(100f, Color(0xFF2609DB))
                    )
                )
            }
        ) {
            composable(route = Screen.Home.name) {
                MyTasksScreen()
            }
            composable(route = Screen.Kanban.name) {
                KanbanScreen()
            }
        }
    }

}