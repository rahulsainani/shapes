package shapes.base.data

import java.util.ArrayDeque
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Stack<T> @Inject constructor() {

    private val stack = ArrayDeque<List<T>>()

    fun push(list: List<T>) {
        stack.push(list)
    }

    fun pop(): List<T>? {
        if (stack.size > 0) {
            return stack.pop()
        }
        return null
    }
}
