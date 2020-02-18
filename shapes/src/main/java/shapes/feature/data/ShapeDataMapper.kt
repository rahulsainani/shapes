package shapes.feature.data

import javax.inject.Inject
import shapes.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity

class ShapeDataMapper @Inject constructor(
    private val shapeTypeDataMapper: ShapeTypeDataMapper
) {

    fun apply(domainEntity: ShapeDomainEntity): ShapeDataEntity =
        ShapeDataEntity(
            id = domainEntity.id,
            type = shapeTypeDataMapper.apply(domainEntity.type)
        )
}
