package com.pedrosantos.declarativemultiplatformist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

class TaskListViewModel : ViewModel() {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
    private val tasks = MutableLiveData<List<Task>>(listOf())
    private val sortingDirection = MutableLiveData(SortingDirection.ASCENDING)

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
                    tasks.value.orEmpty().sortedBy { it.date }
                } else {
                    tasks.value.orEmpty().sortedByDescending { it.date }
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
        return if (content.isNotEmpty() && dateTime.isValidDate()) {
            val dateMs = requireNotNull(dateFormat.parse(dateTime)).time
            tasks.value = tasks.value.orEmpty() + Task(content, dateMs, isUrgent)
            true
        } else {
            false
        }
    }

    private fun String.isValidDate(): Boolean = try {
        dateFormat.parse(this) != null
    } catch (e: Exception) {
        false
    }

    data class State(val tasks: List<Task>, val sortingDirection: SortingDirection)

    enum class SortingDirection {
        ASCENDING, DESCENDING
    }
}

private fun generateDate() = System.currentTimeMillis() + Random.nextInt(1000, 100_000)

/**
 * Class that represents a task.
 */
data class Task(val content: String, val date: Long = generateDate(), val isUrgent: Boolean = false)

