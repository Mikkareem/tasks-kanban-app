package com.techullurgy.tasksapp.data.local.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techullurgy.tasksapp.data.local.daos.TasksDao
import com.techullurgy.tasksapp.data.local.entities.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}