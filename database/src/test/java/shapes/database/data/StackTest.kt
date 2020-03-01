package shapes.database.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class StackTest {

    private val tested = Stack<String>()

    @Test
    fun `when push is called then item is added to stack`() {
        val item = "Item"
        tested.push(item)

        assertEquals(item, tested.pop())
    }

    @Test
    fun `when pop is called on a non empty stack then item is popped stack`() {
        val item = "Item"
        tested.push("Some")
        tested.push("Other")
        tested.push(item)

        assertEquals(item, tested.pop())
    }

    @Test
    fun `when pop is called on an empty stack then null is returned`() {
        assertNull(tested.pop())
    }
}
