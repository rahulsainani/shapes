package shapes.feature.presentation.stats

sealed class StatisticsViewState {
    object Empty : StatisticsViewState()
    data class Content(val items: List<StatisticsItemEntity>) : StatisticsViewState()
}
