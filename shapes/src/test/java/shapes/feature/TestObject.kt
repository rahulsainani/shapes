package shapes.feature

import shapes.base.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity

object TestObject {

    fun shapeDataEntity(
        id: Int = 1,
        type: ShapeDataEntity.ShapeType = ShapeDataEntity.ShapeType.CIRCLE
    ) = ShapeDataEntity(
        id,
        type
    )

    fun shapeDomainEntity(
        id: Int = 1,
        type: ShapeDomainEntity.Type = ShapeDomainEntity.Type.CIRCLE
    ) = ShapeDomainEntity(
        id,
        type
    )
}