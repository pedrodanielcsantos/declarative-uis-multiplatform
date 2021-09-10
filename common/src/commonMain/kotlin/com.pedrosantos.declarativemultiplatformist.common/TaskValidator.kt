package com.pedrosantos.declarativemultiplatformist.common

class TaskValidator {
    fun validate(task: Task): Boolean = task.content.isNotBlank()
}
