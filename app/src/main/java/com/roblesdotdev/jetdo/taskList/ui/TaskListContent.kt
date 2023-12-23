package com.roblesdotdev.jetdo.taskList.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme
import com.roblesdotdev.jetdo.taskList.domain.model.Task
import com.roblesdotdev.jetdo.taskList.ui.components.TaskList

@Composable
fun TaskListContent(
    viewState: TaskListViewState,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    onAddClicked: () -> Unit

) {
    Scaffold(
        floatingActionButton = {
            AddTaskButton(onClick = onAddClicked)
        }
    ) { paddingValues ->
        TaskList(
            tasks = viewState.tasks,
            onRescheduleClicked = onRescheduleClicked,
            onDoneClicked = onDoneClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_task)
        )
    }
}

@Suppress("MagicNumber")
@Preview(
    name = "Light TaskListContentPreview",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night TaskListContentPreview",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TaskListContentPreview() {
    val dumbTasks = (1..10).map { idx ->
        Task(title = "Task number $idx")
    }
    val taskViewState = TaskListViewState(tasks = dumbTasks)
    JetDoTheme {
        TaskListContent(
            viewState = taskViewState,
            onDoneClicked = {},
            onRescheduleClicked = {},
            onAddClicked = {}
        )
    }
}
