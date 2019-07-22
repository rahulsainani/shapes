package shapes.base.database

import androidx.room.TypeConverter
import shapes.base.database.ShapeDataEntity.ShapeType

class ShapeTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Int): ShapeType? =
        when (value) {
            VALUE_CIRCLE -> ShapeType.CIRCLE
            VALUE_SQUARE -> ShapeType.SQUARE
            VALUE_TRIANGLE -> ShapeType.TRIANGLE
            else -> null
        }

    @TypeConverter
    fun dateToTimestamp(type: ShapeType): Int =
        when (type) {
            ShapeType.CIRCLE -> VALUE_CIRCLE
            ShapeType.SQUARE -> VALUE_SQUARE
            ShapeType.TRIANGLE -> VALUE_TRIANGLE
        }
}