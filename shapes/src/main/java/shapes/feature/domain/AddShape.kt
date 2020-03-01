package shapes.feature.domain

import javax.inject.Inject

class AddShape @Inject constructor(
    private val repository: IShapesRepository,
    private val randomGridPositionGenerator: RandomGridPositionGenerator
) {

    suspend fun addShape(shapeType: ShapeDomainEntity.Type) {
        val idsList = repository.getAllShapesOneShot().map { it.id }

        val position = randomGridPositionGenerator.generate(idsList)

        repository.addShape(ShapeDomainEntity(position, shapeType))
    }
}
