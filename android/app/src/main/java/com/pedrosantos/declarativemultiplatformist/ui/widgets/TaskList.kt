package com.pedrosantos.declarativemultiplatformist.ui.widgets

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedrosantos.declarativemultiplatformist.R
import com.pedrosantos.declarativemultiplatformist.Task
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskListViewModel
import java.util.Date

@Composable
fun TaskListScreen(
    state: TaskListViewModel.State?,
    onInvert: () -> Unit,
    onClick: (task: Task) -> Unit,
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val hasTasks = state?.tasks?.isNotEmpty() == true
            TaskListControls(
                sortingDirection = state?.sortingDirection,
                showInvert = hasTasks,
                onInvert = onInvert,
            )
            if (state?.tasks?.isNotEmpty() == true) {
                TaskList(tasks = state.tasks, onClick)
            } else {
                Text(text = stringResource(id = R.string.no_tasks))
            }
        }
    }
}

@Composable
private fun TaskListControls(
    sortingDirection: TaskListViewModel.SortingDirection?,
    showInvert: Boolean,
    onInvert: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextIconButton(
            icon = Icons.Outlined.Add,
            text = stringResource(id = R.string.add_task),
            action = {}
        )

        if (showInvert) {
            Spacer(modifier = Modifier.width(16.dp))

            val isAscending = sortingDirection == TaskListViewModel.SortingDirection.ASCENDING
            TextIconButton(
                icon = if (isAscending) {
                    Icons.Outlined.KeyboardArrowUp
                } else {
                    Icons.Outlined.KeyboardArrowDown
                },
                text = stringResource(if (isAscending) R.string.ascending else R.string.descending),
                onInvert
            )
        }

    }
}

@Composable
private fun TextIconButton(icon: ImageVector, text: String, action: ButtonAction) {
    Button(onClick = action, modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.invert_order),
            )
            Text(text)
        }
    }
}

@Composable
private fun TaskList(tasks: List<Task>, onClick: (task: Task) -> Unit) {
    LazyColumn {
        items(tasks) { TaskCard(it, onClick) }
    }
}

@Composable
private fun TaskCard(task: Task, onClick: (task: Task) -> Unit) {
    Column(
        Modifier.clickable { onClick(task) }
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.body1,
            text = "${task.content}!",
            color = if (task.isUrgent) Color.Red else MaterialTheme.typography.body1.color
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.caption,
            text = "${Date(task.date)}",
        )
        Spacer(modifier = Modifier.height(2.dp))
        Divider(
            color = MaterialTheme.colors.secondary,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

typealias ButtonAction = () -> Unit
