package shapes.base.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ShapesDao {

    @Query("SELECT * FROM shapes")
    fun getAllShapes(): Flowable<List<ShapeDataEntity>>

    @Insert
    fun insert(shapeDataEntity: ShapeDataEntity): Completable

    @Update
    fun update(shapeDataEntity: ShapeDataEntity): Completable

    @Delete
    fun delete(shapeDataEntity: ShapeDataEntity): Completable
}