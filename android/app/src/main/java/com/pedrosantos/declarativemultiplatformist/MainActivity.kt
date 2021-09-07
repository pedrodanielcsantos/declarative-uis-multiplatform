package com.pedrosantos.declarativemultiplatformist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.pedrosantos.declarativemultiplatformist.ui.theme.DeclarativeMultiplatformistTheme
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskListViewModel
import com.pedrosantos.declarativemultiplatformist.ui.widgets.TaskListScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TaskListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.state.observeAsState()
            DeclarativeMultiplatformistTheme {
                TaskListScreen(
                    state,
                    viewModel::invert,
                    viewModel::onClick,
                    viewModel::onSubmit,
                )
            }
        }
    }
}
