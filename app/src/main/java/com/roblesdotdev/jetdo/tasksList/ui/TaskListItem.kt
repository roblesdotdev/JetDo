package com.roblesdotdev.jetdo.tasksList.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.ui.components.AppTextButton
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme
import com.roblesdotdev.jetdo.tasksList.domain.model.Task

@Composable
fun TaskListItem(
    task: Task,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TaskText(title = task.title)

            RowButtons(
                onRescheduleClicked = { onRescheduleClicked(task) },
                onDoneClicked = { onDoneClicked(task) },
            )
        }
    }
}

@Composable
private fun RowButtons(
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AppTextButton(
            text = stringResource(R.string.reschedule),
            onClick = onRescheduleClicked
        )
        AppTextButton(
            text = stringResource(R.string.done),
            onClick = onDoneClicked
        )
    }
}

@Composable
private fun TaskText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(
    name = "Night TaskItem",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light TaskItem",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun TaskItemPreview() {
    JetDoTheme {
        val demoTask = Task(title = "Mi first task item")
        TaskListItem(
            task = demoTask,
            onRescheduleClicked = {},
            onDoneClicked = {}
        )
    }
}
