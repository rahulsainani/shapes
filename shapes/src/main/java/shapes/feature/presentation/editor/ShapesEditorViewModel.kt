package shapes.feature.presentation.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import shapes.base.extensions.launchSafe
import shapes.base.presentation.BaseViewModel
import shapes.feature.domain.AddShape
import shapes.feature.domain.DeleteShape
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.SwitchShape
import shapes.feature.domain.UndoLastAction
import timber.log.Timber
import javax.inject.Inject

class ShapesEditorViewModel @Inject constructor(
    private val retrieveShapes: RetrieveShapes,
    private val addShape: AddShape,
    private val switchShape: SwitchShape,
    private val deleteShape: DeleteShape,
    private val undoLastAction: UndoLastAction
) : BaseViewModel() {

    internal val viewStateLiveData = MutableLiveData<ShapesEditorViewState>()

    init {
        processShapesStream()
    }

    internal fun onCircleClick() = addShape(ShapeDomainEntity.Type.CIRCLE)

    internal fun onSquareClick() = addShape(ShapeDomainEntity.Type.SQUARE)

    internal fun onTriangleClick() = addShape(ShapeDomainEntity.Type.TRIANGLE)

    internal fun onShapeClick(shapeDomainEntity: ShapeDomainEntity) {
        viewModelScope.launchSafe(
            { switchShape.switchShape(shapeDomainEntity) },
            { Timber.e(it, "Error updating shape") }
        )
    }

    internal fun onShapeLongClick(shapeDomainEntity: ShapeDomainEntity) {
        viewModelScope.launchSafe(
            { deleteShape.delete(shapeDomainEntity) },
            { Timber.e(it, "Error deleting shape") }
        )
    }

    internal fun onUndoClick() {
        viewModelScope.launchSafe(
            { undoLastAction.undo() },
            { Timber.e(it, "Error doing onUndoClick") }
        )
    }

    private fun addShape(type: ShapeDomainEntity.Type) {
        viewModelScope.launchSafe(
            { addShape.addShape(type) },
            { Timber.e(it, "Error adding shape") }
        )
    }

    private fun processShapesStream() =
        retrieveShapes
            .retrieveShapes()
            .subscribe(
                { postViewState(it) },
                { Timber.e(it, "Error retrieving shapes") }
            )
            .addToCompositeDisposable()

    private fun postViewState(items: List<ShapeDomainEntity>) =
        if (items.isEmpty()) {
            viewStateLiveData.postValue(ShapesEditorViewState.Empty)
        } else {
            viewStateLiveData.postValue(ShapesEditorViewState.Content(items))
        }
}
