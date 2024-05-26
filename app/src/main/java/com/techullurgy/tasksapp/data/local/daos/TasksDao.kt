package com.techullurgy.tasksapp.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.techullurgy.tasksapp.data.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Upsert
    suspend fun tasks(vararg task: TaskEntity)
}