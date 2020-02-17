package shapes.feature.data

import javax.inject.Inject
import shapes.database.ShapeDataEntity
import shapes.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity

class ShapeDomainMapper @Inject constructor() {

    fun apply(dataEntity: ShapeDataEntity): ShapeDomainEntity {
        val type = when (dataEntity.type) {
            ShapeType.CIRCLE -> ShapeDomainEntity.Type.CIRCLE
            ShapeType.SQUARE -> ShapeDomainEntity.Type.SQUARE
            ShapeType.TRIANGLE -> ShapeDomainEntity.Type.TRIANGLE
        }

        return ShapeDomainEntity(
            dataEntity.id,
            type
        )
    }
}
