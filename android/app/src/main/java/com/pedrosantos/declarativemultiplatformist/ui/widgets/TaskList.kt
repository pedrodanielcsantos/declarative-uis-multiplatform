package com.pedrosantos.declarativemultiplatformist.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedrosantos.declarativemultiplatformist.R
import com.pedrosantos.declarativemultiplatformist.ui.theme.Shapes
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskListViewModel
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskSubmitionResult
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.UiTask
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalMaterialApi
@Composable
fun TaskListScreen(
    state: TaskListViewModel.State?,
    onInvert: () -> Unit,
    onClick: (task: UiTask) -> Unit,
    onSubmit: (String, String, Boolean) -> TaskSubmitionResult
) {
    Surface(color = MaterialTheme.colors.background) {
        val addTaskBottomSheetState = rememberBottomSheetScaffoldState()

        val scope = rememberCoroutineScope()

        BottomSheetScaffold(
            scaffoldState = addTaskBottomSheetState,
            sheetContent = {
                AddTaskScreen(
                    onSubmit = { content, dateTime, isUrgent ->
                        val result = onSubmit(content, dateTime, isUrgent)
                        result.also {
                            if (it is TaskSubmitionResult.Success) {
                                scope.launch { addTaskBottomSheetState.bottomSheetState.collapse() }
                            }
                        }
                    }
                )
            },
            sheetShape = Shapes.medium,
            sheetPeekHeight = 0.dp
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
private fun TaskList(tasks: List<UiTask>, onClick: (task: UiTask) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(
            items = tasks,
            key = { it.hashCode() },
        ) {
            TaskCard(it, onClick)
        }
    }
}

@Composable
private fun TaskCard(task: UiTask, onClick: (task: UiTask) -> Unit) {
    Card(elevation = 4.dp) {
        Column(
            Modifier
                .clickable { onClick(task) }
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.body1,
                text = task.content,
                color = if (task.isUrgent) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.typography.body1.color
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.caption,
                text = "${Date(task.dueTimestamp)}",
            )
        }
    }
}

typealias ButtonAction = () -> Unit
