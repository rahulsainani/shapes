package shapes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shapes")
data class ShapeDataEntity(
    @PrimaryKey val id: Int,
    val type: ShapeType
) {
    enum class ShapeType(val code: Int) {
        CIRCLE(VALUE_CIRCLE),
        SQUARE(VALUE_SQUARE),
        TRIANGLE(VALUE_TRIANGLE)
    }
}
