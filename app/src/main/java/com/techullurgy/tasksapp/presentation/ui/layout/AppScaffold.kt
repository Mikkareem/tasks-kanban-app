package com.techullurgy.tasksapp.presentation.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techullurgy.tasksapp.presentation.navigation.LocalNavHostController

@Composable
fun AppScaffold(
    topBar: @Composable () -> Unit,
    fab: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .drawBehind {
                drawRect(
                    brush = Brush.linearGradient(
                        Pair(0f, Color(0xFF7871EF)),
                        Pair(100f, Color(0xFF2609DB))
                    )
                )
            }
            .safeDrawingPadding(),
        topBar = topBar,
        floatingActionButton = fab
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            content()
        }
    }
}

@Composable
fun AppScaffold(
    title: String,
    backEnabled: Boolean = true,
    fab: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    AppScaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                backEnabled
                    .takeIf { it }
                    ?.let {
                        val navController = LocalNavHostController.current
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                            contentDescription = "Go Back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
            }
        },
        fab = fab
    ) {
        content()
    }
}