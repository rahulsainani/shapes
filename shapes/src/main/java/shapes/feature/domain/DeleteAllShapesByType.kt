package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject

class DeleteAllShapesByType @Inject constructor(
    private val repository: IShapesRepository
) {

    fun delete(shapeType: ShapeDomainEntity.Type): Completable =
        repository.deleteAllShapesByType(shapeType)
}
