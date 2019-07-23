package shapes.base.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ShapesDao {

    @Query("SELECT * FROM shapes")
    fun getAllShapes(): Flowable<List<ShapeDataEntity>>

    @Query("SELECT * FROM shapes")
    fun getAllShapesSingle(): Single<List<ShapeDataEntity>>

    @Insert
    fun insert(shapeDataEntity: ShapeDataEntity): Completable

    @Insert
    fun insertAll(shapeDataEntityList: List<ShapeDataEntity>): Completable

    @Update
    fun update(shapeDataEntity: ShapeDataEntity): Completable

    @Delete
    fun delete(shapeDataEntity: ShapeDataEntity): Completable

    @Query("DELETE FROM shapes WHERE type = :shapeType")
    fun deleteAllShapesByType(shapeType: ShapeDataEntity.ShapeType): Completable

    @Query("DELETE FROM shapes")
    fun deleteAll(): Completable
}