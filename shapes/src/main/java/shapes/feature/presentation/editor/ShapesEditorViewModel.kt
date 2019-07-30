package shapes.feature.presentation.editor

import androidx.lifecycle.MutableLiveData
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import shapes.base.presentation.BaseViewModel
import shapes.base.rx.SchedulerProvider
import shapes.feature.domain.AddShape
import shapes.feature.domain.DeleteShape
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.SwitchShape
import shapes.feature.domain.UndoLastAction
import timber.log.Timber

class ShapesEditorViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val addShape: AddShape,
    private val switchShape: SwitchShape,
    private val deleteShape: DeleteShape,
    private val undoLastAction: UndoLastAction,
    private val schedulerProvider: SchedulerProvider
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

    internal fun onShapeLongClick(shapeDomainEntity: ShapeDomainEntity) {
        deleteShape
            .delete(shapeDomainEntity)
            .subscribe(
                { Timber.d("Shape deleted successfully") },
                { Timber.e(it, "Error deleting shape") }
            )
            .addToCompositeDisposable()
    }

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
            .throttleLast(
                SHAPES_STREAM_THROTTLE_DURATION_MS,
                TimeUnit.MILLISECONDS,
                schedulerProvider.computation()
            )
            .subscribe(
                {
                    Timber.d("Shapes: $it")
                    shapesLiveData.postValue(it)
                },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()

    companion object {
        private const val SHAPES_STREAM_THROTTLE_DURATION_MS = 50L
    }
}
