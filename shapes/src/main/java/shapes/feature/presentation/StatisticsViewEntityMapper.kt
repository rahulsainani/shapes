package shapes.feature.presentation

import io.reactivex.functions.Function
import shapes.base.presentation.StringsProvider
import shapes.feature.R
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.ShapeDomainEntity.Type
import javax.inject.Inject

class StatisticsViewEntityMapper @Inject constructor(
    private val stringsProvider: StringsProvider
) :
    Function<List<ShapeDomainEntity>, List<StatisticsItemEntity>> {

    override fun apply(shapesList: List<ShapeDomainEntity>): List<StatisticsItemEntity> {
        return shapesList
            .groupBy { entity -> entity.type }
            .map { entry -> mapToStatisticsItemEntity(entry) }
    }

    private fun mapToStatisticsItemEntity(entry: Map.Entry<Type, List<ShapeDomainEntity>>): StatisticsItemEntity {
        val shapeName = when (entry.key) {
            Type.SQUARE -> stringsProvider.getString(R.string.square)
            Type.CIRCLE -> stringsProvider.getString(R.string.circle)
            Type.TRIANGLE -> stringsProvider.getString(R.string.triangle)
        }

        return StatisticsItemEntity(shapeName, entry.value.size.toString())
    }
}