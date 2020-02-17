package shapes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Flowable

@Dao
interface ShapesDao {

    @Query("SELECT * FROM shapes")
    fun getAllShapes(): Flowable<List<ShapeDataEntity>>

    @Query("SELECT * FROM shapes")
    suspend fun getAllShapesOneShot(): List<ShapeDataEntity>

    @Insert
    suspend fun insert(shapeDataEntity: ShapeDataEntity)

    @Update
    suspend fun update(shapeDataEntity: ShapeDataEntity)

    @Delete
    suspend fun delete(shapeDataEntity: ShapeDataEntity)

    @Query("DELETE FROM shapes WHERE type = :shapeType")
    suspend fun deleteAllShapesByType(shapeType: ShapeDataEntity.ShapeType)

    @Insert
    suspend fun insertAll(shapeDataEntityList: List<ShapeDataEntity>)

    @Query("DELETE FROM shapes")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(shapeDataEntityList: List<ShapeDataEntity>) {
        deleteAll()
        insertAll(shapeDataEntityList)
    }
}
