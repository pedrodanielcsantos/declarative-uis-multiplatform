package com.pedrosantos.declarativemultiplatformist.ui.widgets

import androidx.compose.animation.*
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pedrosantos.declarativemultiplatformist.R
import com.pedrosantos.declarativemultiplatformist.ui.theme.Shapes
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskListViewModel
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskSubmitionResult
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.UiTask
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun TaskListScreen(
    state: TaskListViewModel.State?,
    onInvert: () -> Unit,
    onClick: (task: UiTask) -> Unit,
    onSwipe: (UiTask, DismissValue) -> Unit,
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val hasVisibleTasks = state?.tasks.orEmpty().count { it.isVisible } != 0
                TaskListControls(
                    sortingDirection = state?.sortingDirection,
                    showInvert = hasVisibleTasks,
                    onInvert = onInvert,
                    onAdd = {
                        scope.launch { addTaskBottomSheetState.bottomSheetState.expand() }
                    }
                )
                if (hasVisibleTasks) {
                    TaskList(tasks = requireNotNull(state).tasks, onClick, onSwipe)
                } else {
                    Text(
                        text = stringResource(id = R.string.no_tasks),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
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

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun TaskList(
    tasks: List<UiTask>,
    onClick: (task: UiTask) -> Unit,
    onSwipe: (UiTask, DismissValue) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(
            items = tasks,
            key = { it.id },
        ) {
            AnimatedVisibility(
                visible = it.isVisible,
                enter = expandVertically(animationSpec = TweenSpec(durationMillis = 10_000)),
                exit = shrinkVertically()
            ) { TaskCard(it, onClick, onSwipe) }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun TaskCard(
    task: UiTask,
    onClick: (task: UiTask) -> Unit,
    onSwipe: (UiTask, DismissValue) -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            onSwipe(task, it)
            false
        }
    )
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(0.33f) },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> MaterialTheme.colors.primaryVariant
                    DismissValue.DismissedToStart -> Color.Green
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
                DismissDirection.StartToEnd -> Icons.Outlined.Warning
                DismissDirection.EndToStart -> Icons.Outlined.CheckCircle
            }
            val scale by animateFloatAsState(
                0.75f.takeIf { dismissState.targetValue == DismissValue.Default } ?: 1.25f
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 16.dp),
                contentAlignment = alignment,
            ) { Icon(icon, contentDescription = "", modifier = Modifier.scale(scale)) }
        },
        dismissContent = {
            Card(
                elevation = animateDpAsState(
                    4.dp.takeIf { dismissState.dismissDirection != null } ?: 0.dp
                ).value,
            ) {
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
    )
}

typealias ButtonAction = () -> Unit
