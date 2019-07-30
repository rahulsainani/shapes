package shapes.feature.data

import io.reactivex.functions.Function
import javax.inject.Inject
import shapes.base.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntity

class ShapesListDomainMapper @Inject constructor(
    private val shapesDomainMapper: ShapeDomainMapper
) : Function<List<ShapeDataEntity>, List<ShapeDomainEntity>> {

    override fun apply(shapesDataList: List<ShapeDataEntity>): List<ShapeDomainEntity> =
        shapesDataList.map { shapesDomainMapper.apply(it) }
}
