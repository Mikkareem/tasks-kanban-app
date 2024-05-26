package com.techullurgy.tasksapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.domain.repositories.TasksRepository
import com.techullurgy.tasksapp.presentation.viewmodels.states.KanbanScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KanbanBoardViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(KanbanScreenState())
    val state: StateFlow<KanbanScreenState> = _state.asStateFlow()

    private lateinit var data: Map<KanbanStatusType, List<Task>>

    init {
        tasksRepository.getAllTasks()
            .onEach {
                data = it.groupBy { t -> t.kanbanStatus }
            }
            .onEach { tasks ->
                _state.update {
                    it.copy(
                        tasks = tasks
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onDragDrop(from: KanbanStatusType, to: KanbanStatusType, fromIndex: Int) {
        if (::data.isInitialized) {
            val current = data[from]!![fromIndex]
            val updatedTask = current.copy(kanbanStatus = to)
            viewModelScope.launch {
                tasksRepository.updateTask(updatedTask)
            }
        }
    }
}