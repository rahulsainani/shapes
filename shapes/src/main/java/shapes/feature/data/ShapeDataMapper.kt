package shapes.feature.data

import io.reactivex.functions.Function
import javax.inject.Inject
import shapes.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity

class ShapeDataMapper @Inject constructor(
    private val shapeTypeDataMapper: ShapeTypeDataMapper
) : Function<ShapeDomainEntity, ShapeDataEntity> {

    override fun apply(domainEntity: ShapeDomainEntity): ShapeDataEntity =
        ShapeDataEntity(
            id = domainEntity.id,
            type = shapeTypeDataMapper.apply(domainEntity.type)
        )
}
