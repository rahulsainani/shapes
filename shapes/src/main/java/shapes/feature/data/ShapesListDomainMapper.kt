package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity
import shapes.feature.domain.ShapeDomainEntityList
import javax.inject.Inject

class ShapesListDomainMapper @Inject constructor(
    private val shapesDomainMapper: ShapeDomainMapper
) : Function<List<ShapeDataEntity>, ShapeDomainEntityList> {

    override fun apply(shapesDataList: List<ShapeDataEntity>): ShapeDomainEntityList =
        shapesDataList
            .map { shapesDomainMapper.apply(it) }
            .run { ShapeDomainEntityList(this) }
}