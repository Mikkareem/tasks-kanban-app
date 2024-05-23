package com.techullurgy.tasksapp.presentation.viewmodels.states

import com.techullurgy.tasksapp.domain.model.Task

data class KanbanScreenState(
    val tasks: List<Task> = emptyList()
)
