package com.pedrosantos.declarativemultiplatformist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosantos.declarativemultiplatformist.common.Task
import com.pedrosantos.declarativemultiplatformist.common.TaskCreator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val tasks = MutableLiveData<List<Task>>(listOf())
    private val sortingDirection = MutableLiveData(SortingDirection.ASCENDING)

    private val taskCreator = TaskCreator()

    private var job: Job? = null

    val state: LiveData<State> = MediatorLiveData<State>().apply {
        addSource(tasks) { updateState(this) }
        addSource(sortingDirection) { updateState(this) }
    }

    private fun updateState(_state: MediatorLiveData<State>) {
        job?.cancel()
        job = viewModelScope.launch {
            _state.value = State(
                if (sortingDirection.value == SortingDirection.ASCENDING) {
                    tasks.value.orEmpty().sortedBy { it.dueTimestamp }
                } else {
                    tasks.value.orEmpty().sortedByDescending { it.dueTimestamp }
                },
                requireNotNull(sortingDirection.value)
            )
        }
    }

    fun invert() {
        if (sortingDirection.value == SortingDirection.ASCENDING) {
            sortingDirection.value = SortingDirection.DESCENDING
        } else {
            sortingDirection.value = SortingDirection.ASCENDING
        }
    }

    fun onClick(task: Task) {
        // Remove clicked task from list.
        tasks.value = tasks.value.orEmpty() - task
    }

    fun onSubmit(
        content: String,
        dateTime: String,
        isUrgent: Boolean
    ): Boolean {
        val result = taskCreator.create(content, dateTime, isUrgent)

        return if (result is TaskCreator.Result.Success) {
            tasks.value = tasks.value.orEmpty() + result.task
            true
        } else {
            false
        }
    }

    data class State(val tasks: List<Task>, val sortingDirection: SortingDirection)

    enum class SortingDirection {
        ASCENDING, DESCENDING
    }
}
