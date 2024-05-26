package com.techullurgy.tasksapp.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

private const val timeInMillis = 400

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    scaleIn(
//        animationSpec = tween(timeInMillis)
    ) + fadeIn(
        animationSpec = tween(timeInMillis)
    )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    scaleOut(
//        animationSpec = tween(timeInMillis)
    ) + fadeOut(
        animationSpec = tween(timeInMillis)
    )
}