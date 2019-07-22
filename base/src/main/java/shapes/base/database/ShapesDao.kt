package shapes.base.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ShapesDao {

    @Query("SELECT * FROM shapes")
    fun getAllShapes(): Flowable<List<ShapeDataEntity>>

    @Insert
    fun insert(shapeDataEntity: ShapeDataEntity): Completable

    @Delete
    fun delete(shapeDataEntity: ShapeDataEntity): Completable
}