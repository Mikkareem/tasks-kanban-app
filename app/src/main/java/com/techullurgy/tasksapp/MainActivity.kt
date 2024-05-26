package com.techullurgy.tasksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.techullurgy.tasksapp.presentation.navigation.AppNavigation
import com.techullurgy.tasksapp.presentation.ui.theme.TasksAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasksAppTheme {
                AppNavigation()
            }
        }
    }
}