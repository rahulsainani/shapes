package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeEntity
import javax.inject.Inject

class ShapeDomainMapper @Inject constructor() : Function<ShapeDataEntity, ShapeEntity> {

    override fun apply(dataEntity: ShapeDataEntity): ShapeEntity =
        when (dataEntity.type) {
            ShapeType.CIRCLE -> ShapeEntity.Circle(
                dataEntity.id,
                Pair(dataEntity.centerX, dataEntity.centerY)
            )
            ShapeType.SQUARE -> ShapeEntity.Square(
                dataEntity.id,
                Pair(dataEntity.centerX, dataEntity.centerY)
            )
            ShapeType.TRIANGLE -> ShapeEntity.Triangle(
                dataEntity.id,
                Pair(dataEntity.centerX, dataEntity.centerY)
            )
        }
}