package com.techullurgy.tasksapp.domain.repositories

import com.techullurgy.tasksapp.data.local.daos.TasksDao
import com.techullurgy.tasksapp.domain.mappers.toTask
import com.techullurgy.tasksapp.domain.mappers.toTaskEntity
import com.techullurgy.tasksapp.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepository(
    private val tasksDao: TasksDao
) {
    fun getAllTasks(): Flow<List<Task>> = tasksDao.getAllTasks().map { it.map { t -> t.toTask() } }

    suspend fun insertTask(task: Task) = tasksDao.tasks(task.toTaskEntity())
    suspend fun updateTask(task: Task) = tasksDao.tasks(task.toTaskEntity())
}