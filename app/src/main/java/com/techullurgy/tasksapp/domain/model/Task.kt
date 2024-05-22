package com.techullurgy.tasksapp.domain.model

data class Task(
    val title: String,
    val description: String,
    val createdOn: String,
    val kanbanStatus: KanbanStatusType
)
