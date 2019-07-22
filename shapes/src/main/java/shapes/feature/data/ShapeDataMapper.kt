package shapes.feature.data

import io.reactivex.functions.Function
import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapeDataEntity.ShapeType
import shapes.feature.domain.ShapeEntity
import javax.inject.Inject

class ShapeDataMapper @Inject constructor() : Function<ShapeEntity, ShapeDataEntity> {

    override fun apply(entity: ShapeEntity): ShapeDataEntity {
        val type = when (entity) {
            is ShapeEntity.Circle -> ShapeType.CIRCLE
            is ShapeEntity.Square -> ShapeType.SQUARE
            is ShapeEntity.Triangle -> ShapeType.TRIANGLE
        }

        return ShapeDataEntity(
            id = entity.id,
            type = type,
            centerX = entity.center.first,
            centerY = entity.center.second
        )
    }
}