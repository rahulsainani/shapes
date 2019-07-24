package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject

class AddShape @Inject constructor(
    private val repository: IShapesRepository,
    private val randomGridPositionGenerator: RandomGridPositionGenerator
) {

    fun addShape(shapeType: ShapeDomainEntity.Type): Completable =
        repository
            .getAllShapes()
            .firstOrError()
            .map { it.map { item -> item.id } }
            .map { randomGridPositionGenerator.generate(it) }
            .map { ShapeDomainEntity(it, shapeType) }
            .flatMapCompletable { repository.addShape(it) }
}