package com.pedrosantos.declarativemultiplatformist.common

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class TaskCreator {
    fun create(
        content: String,
        dateTime: String,
        isUrgent: Boolean
    ): Result {
        val errors = mutableListOf<Error>()
        if (content.isEmpty()) {
            errors.add(Error.InvalidContent)
        }
        var localDateTime: LocalDateTime? = null
        try {
            localDateTime = dateTime.toLocalDateTime()
        } catch (e: Exception) {
            errors.add(Error.InvalidDate)
        }

        return if (errors.isEmpty()) {
            val dateTimeInstant =
                requireNotNull(localDateTime).toInstant(TimeZone.currentSystemDefault())

            Result.Success(Task(content, dateTimeInstant.toEpochMilliseconds(), isUrgent))
        } else {
            Result.Invalid(errors)
        }
    }

    sealed class Result {
        data class Success(val task: Task) : Result()

        data class Invalid(val errors: List<Error>) : Result()
    }

    sealed class Error {
        object InvalidContent : Error()

        object InvalidDate : Error()
    }
}
