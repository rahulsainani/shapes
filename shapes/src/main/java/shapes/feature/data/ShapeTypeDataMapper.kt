package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapeTypeDataMapper @Inject constructor() : Function<ShapeDomainEntity.Type, ShapeType> {

    override fun apply(domainType: ShapeDomainEntity.Type): ShapeType =
        when (domainType) {
            ShapeDomainEntity.Type.CIRCLE -> ShapeType.CIRCLE
            ShapeDomainEntity.Type.SQUARE -> ShapeType.SQUARE
            ShapeDomainEntity.Type.TRIANGLE -> ShapeType.TRIANGLE
        }
}