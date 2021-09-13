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
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pedrosantos.declarativemultiplatformist.R
import com.pedrosantos.declarativemultiplatformist.common.Task
import com.pedrosantos.declarativemultiplatformist.ui.theme.Shapes
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskListViewModel
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskListScreen(
    state: TaskListViewModel.State?,
    onInvert: () -> Unit,
    onClick: (task: Task) -> Unit,
    onSubmit: (String, String, Boolean) -> Boolean
) {
    Surface(color = MaterialTheme.colors.background) {
        val addTaskBottomSheetState = rememberBottomSheetScaffoldState()

        val scope = rememberCoroutineScope()

        BottomSheetScaffold(
            scaffoldState = addTaskBottomSheetState,
            sheetContent = {
                AddTaskScreen(
                    onSubmit = { content, dateTime, isUrgent ->
                        if (onSubmit(content, dateTime, isUrgent)) {
                            scope.launch { addTaskBottomSheetState.bottomSheetState.collapse() }
                            true
                        } else {
                            false
                        }
                    }
                )
            },
            sheetShape = Shapes.medium,
            sheetPeekHeight = 0.dp,
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val hasTasks = state?.tasks?.isNotEmpty() == true
                TaskListControls(
                    sortingDirection = state?.sortingDirection,
                    showInvert = hasTasks,
                    onInvert = onInvert,
                    onAdd = {
                        scope.launch { addTaskBottomSheetState.bottomSheetState.expand() }
                    }
                )
                if (state?.tasks?.isNotEmpty() == true) {
                    TaskList(tasks = state.tasks, onClick)
                } else {
                    Text(text = stringResource(id = R.string.no_tasks))
                }
            }
        }
    }
}

@Composable
private fun TaskListControls(
    sortingDirection: TaskListViewModel.SortingDirection?,
    showInvert: Boolean,
    onInvert: () -> Unit,
    onAdd: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextIconButton(
            icon = Icons.Outlined.Add,
            text = stringResource(id = R.string.add_task),
            action = onAdd
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
            text = "${Date(task.dueTimestamp)}",
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

@Composable
private fun AddTaskScreen(onSubmit: (String, String, Boolean) -> Boolean) {
    var content by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf(false) }
    Column(Modifier.padding(8.dp)) {
        Text(
            text = stringResource(id = R.string.add_task),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.content)) })

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dateTime,
            onValueChange = { dateTime = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.date_format)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.urgent))

            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isUrgent,
                onCheckedChange = { isUrgent = it },
                enabled = true,
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (onSubmit(content, dateTime, isUrgent)) {
                    content = ""
                    dateTime = ""
                    isUrgent = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.submit))
        }
    }
}

typealias ButtonAction = () -> Unit
