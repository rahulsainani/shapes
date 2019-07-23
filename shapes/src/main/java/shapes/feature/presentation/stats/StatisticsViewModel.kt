package shapes.feature.presentation.stats

import androidx.lifecycle.MutableLiveData
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.DeleteAllShapesByType
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import timber.log.Timber
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val deleteAllShapesByType: DeleteAllShapesByType,
    private val statisticsViewEntityMapper: StatisticsViewEntityMapper
) : BaseViewModel() {

    internal val statsListLiveData = MutableLiveData<List<StatisticsItemEntity>>()

    init {
        processShapesStream()
    }

    internal fun onItemClick(shapeType: ShapeDomainEntity.Type) {
        deleteAllShapesByType
            .delete(shapeType)
            .subscribe(
                { Timber.e("All shapes of type deleted successfully") },
                { Timber.e(it, "Error deleting shapes of type") }
            )
            .addToCompositeDisposable()
    }

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .map(statisticsViewEntityMapper)
            .subscribe(
                { statsListLiveData.postValue(it) },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()
}