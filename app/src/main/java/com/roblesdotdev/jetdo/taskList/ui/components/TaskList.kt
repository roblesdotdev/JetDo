package com.roblesdotdev.jetdo.taskList.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme
import com.roblesdotdev.jetdo.taskList.domain.model.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(tasks) { task ->
            TaskListItem(
                task = task,
                onRescheduleClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked
            )
        }
    }
}

@Preview(
    name = "Light TaskListPreview",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night TaskListPreview",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TaskListPreview() {
    @Suppress("MagicNumber")
    val dumbTasks = (1..10).map { idx ->
        Task(title = "Task number $idx")
    }
    JetDoTheme {
        TaskList(
            tasks = dumbTasks,
            onRescheduleClicked = {},
            onDoneClicked = {}
        )
    }
}
