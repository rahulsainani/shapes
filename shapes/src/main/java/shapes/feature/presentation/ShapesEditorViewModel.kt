package shapes.feature.presentation

import androidx.lifecycle.MutableLiveData
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.AddShape
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeEntity
import timber.log.Timber
import javax.inject.Inject

class ShapesEditorViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val addShape: AddShape
) : BaseViewModel() {

    internal val shapesLiveData = MutableLiveData<List<ShapeEntity>>()

    init {
        processShapesStream()
    }

    internal fun onTriangleClick() = onButtonClick(ShapeEntity.Triangle(0, Pair(100f, 100f)))

    private fun onButtonClick(shapeEntity: ShapeEntity) =
        addShape
            .addShape(shapeEntity)
            .subscribe(
                { Timber.d("Shape added successfully") },
                { Timber.e(it, "Error adding shape") }
            )
            .addToCompositeDisposable()

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .map { it.shapes }
            .subscribe(
                {
                    Timber.d("Shapes: $it")
                    shapesLiveData.postValue(it)
                },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()

}