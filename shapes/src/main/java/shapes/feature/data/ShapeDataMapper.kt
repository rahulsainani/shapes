package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapeDataMapper @Inject constructor() : Function<ShapeDomainEntity, ShapeDataEntity> {

    override fun apply(domainEntity: ShapeDomainEntity): ShapeDataEntity {
        val type = when (domainEntity.type) {
            ShapeDomainEntity.Type.CIRCLE -> ShapeType.CIRCLE
            ShapeDomainEntity.Type.SQUARE -> ShapeType.SQUARE
            ShapeDomainEntity.Type.TRIANGLE -> ShapeType.TRIANGLE
        }

        return ShapeDataEntity(
            id = domainEntity.id,
            type = type
        )
    }
}