package shapes.feature.presentation

import androidx.lifecycle.MutableLiveData
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ShapesEditorViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val addShape: AddShape,
    private val switchShape: SwitchShape,
    private val undoLastAction: UndoLastAction
) : BaseViewModel() {

    internal val shapesLiveData = MutableLiveData<List<ShapeDomainEntity>>()

    init {
        processShapesStream()
    }

    internal fun onCircleClick() = addShape(ShapeDomainEntity.Type.CIRCLE)

    internal fun onSquareClick() = addShape(ShapeDomainEntity.Type.SQUARE)

    internal fun onTriangleClick() = addShape(ShapeDomainEntity.Type.TRIANGLE)

    internal fun onShapeClick(shapeDomainEntity: ShapeDomainEntity) =
        switchShape
            .switchShape(shapeDomainEntity)
            .subscribe(
                { Timber.d("Shape updated successfully") },
                { Timber.e(it, "Error updating shape") }
            )
            .addToCompositeDisposable()

    internal fun onUndoClick() {
        undoLastAction
            .undo()
            .subscribe(
                { Timber.d("Undo successful") },
                { Timber.e(it, "Error doing onUndoClick") }
            )
            .addToCompositeDisposable()
    }

    private fun addShape(type: ShapeDomainEntity.Type) =
        addShape
            .addShape(type)
            .subscribe(
                { Timber.d("Shape added successfully") },
                { Timber.e(it, "Error adding shape") }
            )
            .addToCompositeDisposable()

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .map { it.shapes }
            .throttleLast(50, TimeUnit.MILLISECONDS)
            .subscribe(
                {
                    Timber.d("Shapes: $it")
                    shapesLiveData.postValue(it)
                },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()
}