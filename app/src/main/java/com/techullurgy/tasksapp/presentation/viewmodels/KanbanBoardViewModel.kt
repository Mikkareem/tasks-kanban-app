package com.techullurgy.tasksapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.tasksapp.domain.repositories.TasksRepository
import com.techullurgy.tasksapp.presentation.viewmodels.states.KanbanScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class KanbanBoardViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(KanbanScreenState())
    val state: StateFlow<KanbanScreenState> = _state.asStateFlow()

    init {
        tasksRepository.getAllTasks()
            .onEach { tasks ->
                _state.update {
                    it.copy(
                        tasks = tasks
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}