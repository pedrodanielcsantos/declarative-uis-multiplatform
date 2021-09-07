package com.pedrosantos.declarativemultiplatformist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosantos.declarativemultiplatformist.Task
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val tasks = MutableLiveData<List<Task>>()
    private val sortingDirection = MutableLiveData(SortingDirection.ASCENDING)

    private var job: Job? = null

    val state: LiveData<State> = MediatorLiveData<State>().apply {
        addSource(tasks) { updateState(this) }
        addSource(sortingDirection) { updateState(this) }
    }

    init {
        // Dummy set of tasks.
        tasks.value = listOf(
            Task(content = "Buy milk", isUrgent = true),
            Task(content = "Today arrived with a crash of my car through the garage door."),
            Task(content = "My dentist tells me that chewing bricks is very bad for your teeth."),
            Task("This book is sure to liquefy your brain."),
            Task(
                """
                She moved forward only because she trusted that the ending she now was going through 
                must be followed by a new beginning."
                """
            ),
            Task("He stomped on his fruit loops and thus became a cereal killer."),
            Task("The swirled lollipop had issues with the pop rock candy."),
            Task("Seek success, but always be prepared for random cats."),
            Task("Pink horses galloped across the sea."),
            Task("A quiet house is nice until you are ordered to stay in it for months."),
            Task("Flesh-colored yoga pants were far worse than even he feared."),
            Task("She saw the brake lights, but not in time.", isUrgent = true),
            Task("For the 216th time, he said he would quit drinking soda after this last Coke."),
            Task("Three years later, the coffin was still full of Jello."),
            Task("Just because the water is red doesn't mean you can't drink it."),
            Task("The knives were out and she was sharpening hers.", isUrgent = true),
            Task("Everybody should read Chaucer to improve their everyday vocabulary."),
            Task("The pigs were insulted that they were named hamburgers."),
            Task("Whenever he saw a red flag warning at the beach he grabbed his surfboard."),
            Task("Everyone pretends to like wheat until you mention barley."),
            Task("We have never been to Asia, nor have we visited Africa.", isUrgent = true),
            Task("The knives were out and she was sharpening hers.", isUrgent = true),
            Task("Everybody should read Chaucer to improve their everyday vocabulary."),
            Task("The pigs were insulted that they were named hamburgers."),
            Task("Whenever he saw a red flag warning at the beach he grabbed his surfboard."),
            Task("Everyone pretends to like wheat until you mention barley."),
            Task("We have never been to Asia, nor have we visited Africa.", isUrgent = true),
            Task("The knives were out and she was sharpening hers.", isUrgent = true),
            Task("Everybody should read Chaucer to improve their everyday vocabulary."),
            Task("The pigs were insulted that they were named hamburgers."),
            Task("Whenever he saw a red flag warning at the beach he grabbed his surfboard."),
            Task("Everyone pretends to like wheat until you mention barley."),
            Task("We have never been to Asia, nor have we visited Africa.", isUrgent = true),
        )
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

    data class State(val tasks: List<Task>, val sortingDirection: SortingDirection)

    enum class SortingDirection {
        ASCENDING, DESCENDING
    }
}
