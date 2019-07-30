package shapes.feature.data

import io.reactivex.functions.Function
import javax.inject.Inject
import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity

class ShapeDomainMapper @Inject constructor() : Function<ShapeDataEntity, ShapeDomainEntity> {

    override fun apply(dataEntity: ShapeDataEntity): ShapeDomainEntity {
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
