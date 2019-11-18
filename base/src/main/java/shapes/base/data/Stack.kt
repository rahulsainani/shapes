package shapes.base.data

import java.util.ArrayDeque
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Stack<T> @Inject constructor() {

    private val stack = ArrayDeque<T>()

    fun push(item: T) {
        stack.push(item)
    }

    fun pop(): T? {
        if (stack.size > 0) {
            return stack.pop()
        }
        return null
    }
}
