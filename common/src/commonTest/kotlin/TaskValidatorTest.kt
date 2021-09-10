import com.pedrosantos.declarativemultiplatformist.common.Task
import com.pedrosantos.declarativemultiplatformist.common.TaskValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TaskValidatorTest {
    @Test
    fun taskCreatedSuccessfully() {
        val task = Task(content = "", dueTimestamp = 0, isUrgent = false)
        assertNotNull(task)
    }

    @Test
    fun emptyTaskIsInvalid() {
        val task = Task(content = "", dueTimestamp = 0, isUrgent = false)
        val taskValidator = TaskValidator()
        assertFalse(taskValidator.validate(task))
    }

    @Test
    fun taskWithContentIsValid() {
        val task = Task(content = "Content", dueTimestamp = 0, isUrgent = false)
        val taskValidator = TaskValidator()
        assertTrue(taskValidator.validate(task))
    }
}
