package com.techullurgy.tasksapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.presentation.viewmodels.states.KanbanScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KanbanBoardViewModel: ViewModel() {
    private val _state = MutableStateFlow(KanbanScreenState())
    val state: StateFlow<KanbanScreenState> = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                tasks = listOf(
                    Task(
                        title = "Cooking Food 1",
                        description = "Cooking food 1 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 2",
                        description = "Cooking food 2 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("Completed")
                    ),
                    Task(
                        title = "Cooking Food 3",
                        description = "Cooking food 3 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 4",
                        description = "Cooking food 4 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 5",
                        description = "Cooking food 5 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("Completed")
                    ),
                    Task(
                        title = "Cooking Food 6",
                        description = "Cooking food 6 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 7",
                        description = "Cooking food 7 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 8",
                        description = "Cooking food 8 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("Completed")
                    ),
                    Task(
                        title = "Cooking Food 9",
                        description = "Cooking food 9 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),
                    Task(
                        title = "Cooking Food 10",
                        description = "Cooking food 10 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("Old")
                    ),
                    Task(
                        title = "Cooking Food 11",
                        description = "Cooking food 11 description",
                        createdOn = "2 days ago",
                        kanbanStatus = KanbanStatusType("New")
                    ),

                )
            )
        }
    }
}