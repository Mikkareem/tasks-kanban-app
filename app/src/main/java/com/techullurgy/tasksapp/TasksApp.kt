package com.techullurgy.tasksapp

import android.app.Application
import com.techullurgy.tasksapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TasksApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TasksApp)
            modules(appModule)
        }
    }
}