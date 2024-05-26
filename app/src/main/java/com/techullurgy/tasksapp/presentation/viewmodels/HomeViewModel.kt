package com.techullurgy.tasksapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        tasksRepository.getAllTasks()
            .onEach { tasks ->
                _state.update {
                    it.copy(
                        myTasks = tasks
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}

data class HomeScreenState(
    val myTasks: List<Task> = emptyList()
)