package com.techullurgy.tasksapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.techullurgy.tasksapp.data.local.daos.TasksDao
import com.techullurgy.tasksapp.data.local.dbs.TasksDatabase
import com.techullurgy.tasksapp.data.local.sampleTasks
import com.techullurgy.tasksapp.domain.mappers.toTaskEntity
import com.techullurgy.tasksapp.domain.repositories.TasksRepository
import com.techullurgy.tasksapp.presentation.viewmodels.HomeViewModel
import com.techullurgy.tasksapp.presentation.viewmodels.KanbanBoardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single<TasksDatabase> {
        val context: Context = get()
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            "techullurgy-tasks-db"
        )
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread {
                            runBlocking(Dispatchers.IO) {
                                get<TasksDatabase>().tasksDao()
                                    .tasks(*sampleTasks.map { it.toTaskEntity() }.toTypedArray())
                            }
                        }.start()
                    }
                }
            )
            .build()
    }

    single<TasksDao> {
        get<TasksDatabase>().tasksDao()
    }

    singleOf(::TasksRepository)
    viewModelOf(::KanbanBoardViewModel)
    viewModelOf(::HomeViewModel)
}