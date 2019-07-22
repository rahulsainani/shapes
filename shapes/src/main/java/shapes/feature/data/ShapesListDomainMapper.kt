package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity
import shapes.feature.domain.ShapeEntityList
import javax.inject.Inject

class ShapesListDomainMapper @Inject constructor(
    private val shapesDomainMapper: ShapeDomainMapper
) : Function<List<ShapeDataEntity>, ShapeEntityList> {

    override fun apply(shapesDataList: List<ShapeDataEntity>): ShapeEntityList =
        shapesDataList
            .map { shapesDomainMapper.apply(it) }
            .run { ShapeEntityList(this) }
}