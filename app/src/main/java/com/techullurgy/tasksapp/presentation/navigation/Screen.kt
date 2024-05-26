package com.techullurgy.tasksapp.presentation.navigation

sealed class Screen(val name: String) {
    data object Kanban : Screen("Kanban")
    data object Home : Screen("Home")
}