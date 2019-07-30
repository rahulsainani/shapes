package shapes.base.data

import java.util.ArrayDeque
import javax.inject.Inject
import javax.inject.Singleton
import shapes.base.database.ShapeDataEntity

@Singleton
class Stack @Inject constructor() {

    private val stack = ArrayDeque<List<ShapeDataEntity>>()

    fun push(list: List<ShapeDataEntity>) {
        stack.push(list)
    }

    fun pop(): List<ShapeDataEntity>? {
        if (stack.size > 0) {
            return stack.pop()
        }
        return null
    }
}
