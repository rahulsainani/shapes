package shapes.feature.data

import javax.inject.Inject
import shapes.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity

class ShapesListDomainMapper @Inject constructor(
    private val shapesDomainMapper: ShapeDomainMapper
) {

    fun apply(shapesDataList: List<ShapeDataEntity>): List<ShapeDomainEntity> =
        shapesDataList.map { shapesDomainMapper.apply(it) }
}
