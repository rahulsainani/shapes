package shapes.feature.presentation.stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import shapes.base.extensions.launchSafe
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.DeleteAllShapesByType
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import timber.log.Timber

class StatisticsViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val deleteAllShapesByType: DeleteAllShapesByType,
    private val statisticsViewEntityMapper: StatisticsViewEntityMapper
) : BaseViewModel() {

    internal val statsViewStateLiveData = MutableLiveData<StatisticsViewState>()

    init {
        processShapesStream()
    }

    internal fun onItemClick(shapeType: ShapeDomainEntity.Type) {
        viewModelScope.launchSafe(
            { deleteAllShapesByType.delete(shapeType) },
            { Timber.e(it, "Error deleting shapes of type") }
        )
    }

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .map(statisticsViewEntityMapper)
            .subscribe(
                { postViewState(it) },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()

    private fun postViewState(items: List<StatisticsItemEntity>) =
        if (items.isEmpty()) {
            statsViewStateLiveData.postValue(StatisticsViewState.Empty)
        } else {
            statsViewStateLiveData.postValue(StatisticsViewState.Content(items))
        }
}
