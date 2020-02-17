package shapes.feature.data

import shapes.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapesListDomainMapper @Inject constructor(
    private val shapesDomainMapper: ShapeDomainMapper
) {

    fun apply(shapesDataList: List<ShapeDataEntity>): List<ShapeDomainEntity> =
        shapesDataList.map { shapesDomainMapper.apply(it) }
}
