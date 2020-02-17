package shapes.feature.domain

import javax.inject.Inject

class DeleteAllShapesByType @Inject constructor(
    private val repository: IShapesRepository
) {

    suspend fun delete(shapeType: ShapeDomainEntity.Type) =
        repository.deleteAllShapesByType(shapeType)
}
