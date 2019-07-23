package shapes.feature.presentation

import androidx.lifecycle.MutableLiveData
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.RetrieveShapes
import timber.log.Timber
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val statisticsViewEntityMapper: StatisticsViewEntityMapper
) : BaseViewModel() {

    internal val statsListLiveData = MutableLiveData<List<StatisticsItemEntity>>()

    init {
        processShapesStream()
    }

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .firstOrError()
            .map(statisticsViewEntityMapper)
            .subscribe(
                { statsListLiveData.postValue(it) },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()
}