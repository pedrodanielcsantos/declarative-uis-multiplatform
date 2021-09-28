package com.pedrosantos.declarativemultiplatformist.ui.viewmodel

import androidx.lifecycle.*
import com.pedrosantos.declarativemultiplatformist.common.Task
import com.pedrosantos.declarativemultiplatformist.common.TaskCreator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.random.Random

class TaskListViewModel : ViewModel() {
    private val tasks = MutableLiveData<List<UiTask>>(listOf())
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
                    tasks.value.orEmpty().sortedBy { it.dueTimestamp }
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

    fun onClick(task: UiTask) {
        // Hide clicked task on the list.
        tasks.value = tasks.value.orEmpty().map {
            it.takeUnless { it.id == task.id } ?: it.copy(isVisible = false)
        }
    }

    @ExperimentalStdlibApi
    fun onSubmit(
        content: String,
        dateTime: String,
        isUrgent: Boolean
    ): TaskSubmitionResult {
        val result = taskCreator.create(content, dateTime, isUrgent)

        return if (result is TaskCreator.Result.Success) {
            tasks.value = tasks.value.orEmpty() + result.task.toUiTask()
            TaskSubmitionResult.Success
        } else {
            val errors = (result as TaskCreator.Result.Invalid).errors
            TaskSubmitionResult.Error(
                buildList {
                    if (errors.contains(TaskCreator.Error.InvalidContent)) {
                        add(TaskSubmitionError.INVALID_CONTENT)
                    }
                    if (errors.contains(TaskCreator.Error.InvalidDate)) {
                        add(TaskSubmitionError.INVALID_DATE)
                    }
                }
            )
        }
    }

    data class State(val tasks: List<UiTask>, val sortingDirection: SortingDirection)

    enum class SortingDirection {
        ASCENDING, DESCENDING
    }

    private fun Task.toUiTask() = UiTask(
        content = content,
        dueTimestamp = dueTimestamp,
        isUrgent = isUrgent,
        isVisible = true
    )
}

enum class TaskSubmitionError {
    INVALID_DATE, INVALID_CONTENT
}

sealed class TaskSubmitionResult {
    object Success : TaskSubmitionResult()

    data class Error(val errors: List<TaskSubmitionError>) : TaskSubmitionResult()
}

data class UiTask(
    val id: Long = Random.nextLong(),
    val content: String,
    val dueTimestamp: Long,
    val isUrgent: Boolean,
    val isVisible: Boolean
)
