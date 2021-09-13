import com.pedrosantos.declarativemultiplatformist.common.Task
import com.pedrosantos.declarativemultiplatformist.common.TaskCreator
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class TaskCreatorTest {
    @Test
    fun taskCreatedSuccessfully() {
        val task = Task(content = "", dueTimestamp = 0, isUrgent = false)
        assertNotNull(task)
    }

    @Test
    fun emptyTaskIsInvalid() {
        val taskValidator = TaskCreator()
        val result = taskValidator.create("", "", false)
        assertIs<TaskCreator.Result.Invalid>(result)
        assertEquals(2, result.errors.size)
        assertContains(result.errors, TaskCreator.Error.InvalidContent)
        assertContains(result.errors, TaskCreator.Error.InvalidDate)
    }

    @Test
    fun emptyDueDate() {
        val taskValidator = TaskCreator()
        val result = taskValidator.create("task", "", false)
        assertIs<TaskCreator.Result.Invalid>(result)
        assertEquals(1, result.errors.size)
        assertContains(result.errors, TaskCreator.Error.InvalidDate)
    }

    @Test
    fun invalidDueDate() {
        val taskValidator = TaskCreator()
        val result = taskValidator.create("task", "2021-09-13 14:14", false)
        assertIs<TaskCreator.Result.Invalid>(result)
        assertEquals(1, result.errors.size)
        assertContains(result.errors, TaskCreator.Error.InvalidDate)
    }

    @Test
    fun taskWithContentIsValid() {
        val taskValidator = TaskCreator()
        val dateTime = "2021-09-13T14:14"
        val result = taskValidator.create("task", dateTime, false)
        val dateTimeInstant = dateTime.toLocalDateTime().toInstant(TimeZone.currentSystemDefault())
        assertIs<TaskCreator.Result.Success>(result)
        assertEquals("task", result.task.content)
        assertEquals(dateTimeInstant.toEpochMilliseconds(), result.task.dueTimestamp)
        assertFalse(result.task.isUrgent)
    }
}
