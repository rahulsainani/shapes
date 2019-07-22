package shapes.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ShapeDataEntity::class], version = 1)
@TypeConverters(ShapeTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shapesDao(): ShapesDao
}