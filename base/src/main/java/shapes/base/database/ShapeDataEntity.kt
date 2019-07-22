package shapes.base.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shapes")
data class ShapeDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: ShapeType,
    val centerX: Float,
    val centerY: Float
) {
    enum class ShapeType(val code: Int) {
        CIRCLE(VALUE_CIRCLE),
        SQUARE(VALUE_SQUARE),
        TRIANGLE(VALUE_TRIANGLE)
    }
}