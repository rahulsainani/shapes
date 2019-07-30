package shapes.feature.presentation.stats

import androidx.recyclerview.widget.DiffUtil

class StatisticsDiffCallback : DiffUtil.ItemCallback<StatisticsItemEntity>() {

    override fun areItemsTheSame(
        oldItem: StatisticsItemEntity,
        newItem: StatisticsItemEntity
    ): Boolean =
        areContentsTheSame(oldItem, newItem)

    override fun areContentsTheSame(
        oldItem: StatisticsItemEntity,
        newItem: StatisticsItemEntity
    ): Boolean = oldItem == newItem
}
