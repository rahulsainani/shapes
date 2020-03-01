package shapes.feature.domain

import kotlinx.coroutines.flow.Flow

interface IShapesRepository {

    fun getAllShapes(): Flow<List<ShapeDomainEntity>>

    suspend fun getAllShapesOneShot(): List<ShapeDomainEntity>

    suspend fun addShape(shapeDomainEntity: ShapeDomainEntity)

    suspend fun updateShape(shapeDomainEntity: ShapeDomainEntity)

    suspend fun delete(shapeDomainEntity: ShapeDomainEntity)

    suspend fun deleteAllShapesByType(shapeType: ShapeDomainEntity.Type)

    suspend fun undo()
}
