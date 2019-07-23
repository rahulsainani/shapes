package shapes.feature.data

import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapeDataMapper @Inject constructor() {

    fun apply(shapeType: ShapeDomainEntity.Type, id: Int): ShapeDataEntity {
        val type = when (shapeType) {
            ShapeDomainEntity.Type.CIRCLE -> ShapeType.CIRCLE
            ShapeDomainEntity.Type.SQUARE -> ShapeType.SQUARE
            ShapeDomainEntity.Type.TRIANGLE -> ShapeType.TRIANGLE
        }

        return ShapeDataEntity(
            id = id,
            type = type
        )
    }
}