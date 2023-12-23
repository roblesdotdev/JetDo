package com.roblesdotdev.jetdo.taskList.ui

import com.roblesdotdev.jetdo.taskList.domain.model.Task

data class TaskListViewState(
    val isLoading: Boolean = true,
    val tasks: List<Task>
)
