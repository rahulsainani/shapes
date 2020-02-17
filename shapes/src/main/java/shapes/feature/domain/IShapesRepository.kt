package shapes.feature.domain

import io.reactivex.Flowable

interface IShapesRepository {

    fun getAllShapes(): Flowable<List<ShapeDomainEntity>>

    suspend fun getAllShapesOneShot(): List<ShapeDomainEntity>

    suspend fun addShape(shapeDomainEntity: ShapeDomainEntity)

    suspend fun updateShape(shapeDomainEntity: ShapeDomainEntity)

    suspend fun delete(shapeDomainEntity: ShapeDomainEntity)

    suspend fun deleteAllShapesByType(shapeType: ShapeDomainEntity.Type)

    suspend fun undo()
}
