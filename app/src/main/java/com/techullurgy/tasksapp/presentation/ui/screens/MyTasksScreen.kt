package com.techullurgy.tasksapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.presentation.navigation.LocalNavHostController
import com.techullurgy.tasksapp.presentation.navigation.Screen
import com.techullurgy.tasksapp.presentation.ui.components.KanbanIcon
import com.techullurgy.tasksapp.presentation.ui.components.TaskItem
import com.techullurgy.tasksapp.presentation.ui.layout.AppScaffold
import com.techullurgy.tasksapp.presentation.ui.theme.TasksAppTheme
import com.techullurgy.tasksapp.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyTasksScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    MyTasksScreen(
        tasks = state.myTasks
    )
}

@Composable
private fun MyTasksScreen(
    tasks: List<Task>
) {
    val navController = LocalNavHostController.current

    AppScaffold(
        title = "Home",
        backEnabled = false,
        fab = {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .background(Color(0xFF43CC49))
                    .padding(16.dp)
            ) {
                Text(text = "Create Task", style = MaterialTheme.typography.labelLarge)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "My Tasks",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold
                )

                KanbanIcon {
                    navController.navigate(Screen.Kanban.name)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(tasks) {
                    TaskItem(task = it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyTasksScreenPreview() {
    TasksAppTheme {
        MyTasksScreen(
            tasks = listOf(
                Task(0, "Cooking Food", "description", "2 days ago", KanbanStatusType("New")),
                Task(0, "Cooking Food", "description", "2 days ago", KanbanStatusType("New")),
                Task(0, "Cooking Food", "description", "2 days ago", KanbanStatusType("New")),
                Task(0, "Cooking Food", "description", "2 days ago", KanbanStatusType("New")),
            )
        )
    }
}