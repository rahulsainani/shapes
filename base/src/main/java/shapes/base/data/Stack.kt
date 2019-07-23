package shapes.base.data

import shapes.base.database.ShapeDataEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

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