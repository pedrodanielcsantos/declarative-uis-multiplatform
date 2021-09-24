package com.pedrosantos.declarativemultiplatformist.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pedrosantos.declarativemultiplatformist.R
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskSubmitionError
import com.pedrosantos.declarativemultiplatformist.ui.viewmodel.TaskSubmitionResult

@Composable
fun AddTaskScreen(onSubmit: (String, String, Boolean) -> TaskSubmitionResult) {
    var content by rememberSaveable { mutableStateOf("") }
    var dateTime by rememberSaveable { mutableStateOf("") }
    var isUrgent by rememberSaveable { mutableStateOf(false) }
    var isContentError by rememberSaveable { mutableStateOf(false) }
    var isDateTimeError by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.padding(8.dp)) {
        Text(
            text = stringResource(id = R.string.add_task),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                isContentError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.content)) },
            isError = isContentError,
        )
        if (isContentError) {
            Text(
                stringResource(R.string.content_error),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dateTime,
            onValueChange = {
                dateTime = it
                isDateTimeError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.date_format)) },
            isError = isDateTimeError
        )

        if (isDateTimeError) {
            Text(
                stringResource(R.string.date_format_error),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error
            )
        }

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
                when (val result = onSubmit(content, dateTime, isUrgent)) {
                    is TaskSubmitionResult.Success -> {
                        content = ""
                        dateTime = ""
                        isUrgent = false
                    }
                    is TaskSubmitionResult.Error -> {
                        isContentError =
                            result.errors.contains(TaskSubmitionError.INVALID_CONTENT)
                        isDateTimeError =
                            result.errors.contains(TaskSubmitionError.INVALID_DATE)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text(stringResource(R.string.submit)) }
    }
} 
