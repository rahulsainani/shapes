package shapes.feature.data

import javax.inject.Inject
import shapes.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeDomainEntity

class ShapeTypeDataMapper @Inject constructor() {

    fun apply(domainType: ShapeDomainEntity.Type): ShapeType =
        when (domainType) {
            ShapeDomainEntity.Type.CIRCLE -> ShapeType.CIRCLE
            ShapeDomainEntity.Type.SQUARE -> ShapeType.SQUARE
            ShapeDomainEntity.Type.TRIANGLE -> ShapeType.TRIANGLE
        }
}
