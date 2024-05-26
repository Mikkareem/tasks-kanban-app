package com.techullurgy.tasksapp.domain.mappers

import com.techullurgy.tasksapp.data.local.entities.TaskEntity
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task

fun TaskEntity.toTask() = Task(
    id = id,
    title = title,
    description = description,
    createdOn = "2 days ago",
    kanbanStatus = KanbanStatusType(value = kanbanAttribute)
)

fun Task.toTaskEntity() = TaskEntity(
    id = id,
    title = title,
    description = description,
    kanbanAttribute = kanbanStatus.value
)