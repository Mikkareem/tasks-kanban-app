package com.techullurgy.tasksapp.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.presentation.ui.components.TaskItem
import com.techullurgy.tasksapp.presentation.ui.layout.KanbanBoard
import com.techullurgy.tasksapp.presentation.viewmodels.KanbanBoardViewModel

@Composable
fun KanbanScreen(
    viewModel: KanbanBoardViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    var groups by remember {
        mutableStateOf<Map<KanbanStatusType, List<Task>>>(emptyMap())
    }

    LaunchedEffect(key1 = state) {
        groups = state.tasks.groupBy { it.kanbanStatus }
    }

    KanbanBoard(groups = groups, onDragDrop = { _, _, _ -> }) {
        TaskItem(task = it)
    }
}