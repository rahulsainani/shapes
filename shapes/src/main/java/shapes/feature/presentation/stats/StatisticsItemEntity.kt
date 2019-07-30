package shapes.feature.presentation.stats

import shapes.feature.domain.ShapeDomainEntity

data class StatisticsItemEntity(
    val shapeName: String,
    val count: String,
    val shapeType: ShapeDomainEntity.Type
)
