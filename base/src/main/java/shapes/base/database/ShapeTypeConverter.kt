package shapes.base.database

import androidx.room.TypeConverter
import shapes.base.database.ShapeDataEntity.ShapeType

class ShapeTypeConverter {
    @TypeConverter
    fun fromIntToType(value: Int): ShapeType? =
        when (value) {
            VALUE_CIRCLE -> ShapeType.CIRCLE
            VALUE_SQUARE -> ShapeType.SQUARE
            VALUE_TRIANGLE -> ShapeType.TRIANGLE
            else -> null
        }

    @TypeConverter
    fun fromTypeToInt(type: ShapeType): Int = type.code
}