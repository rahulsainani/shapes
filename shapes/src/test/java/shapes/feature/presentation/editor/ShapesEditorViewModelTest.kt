package shapes.feature.presentation.editor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shapes.feature.TestObject
import shapes.feature.domain.AddShape
import shapes.feature.domain.DeleteShape
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.SwitchShape
import shapes.feature.domain.UndoLastAction
import shapes.test.core.CoroutinesExtension
import shapes.test.core.InstantTask

@ExperimentalCoroutinesApi
@InstantTask
@CoroutinesExtension
internal class ShapesEditorViewModelTest {

    private val retrieveShapes: RetrieveShapes = mock()
    private val addShape: AddShape = mock()
    private val switchShape: SwitchShape = mock()
    private val deleteShape: DeleteShape = mock()
    private val undoLastAction: UndoLastAction = mock()

    private lateinit var tested: ShapesEditorViewModel

    private val shapesSteam = BehaviorSubject.create<List<ShapeDomainEntity>>()

    @BeforeEach
    fun setup() {
        whenever(retrieveShapes.retrieveShapes())
            .thenReturn(shapesSteam.toFlowable(BackpressureStrategy.LATEST))
        tested = ShapesEditorViewModel(
            retrieveShapes, addShape, switchShape, deleteShape, undoLastAction
        )
    }

    @Test
    fun `shapes list content is posted to live data`() {
        val shapesList = listOf<ShapeDomainEntity>(mock(), mock())
        shapesSteam.onNext(shapesList)

        assertEquals(ShapesEditorViewState.Content(shapesList), tested.viewStateLiveData.value)
    }

    @Test
    fun `empty state is posted to live data`() {
        val shapesList = emptyList<ShapeDomainEntity>()
        shapesSteam.onNext(shapesList)

        assertEquals(ShapesEditorViewState.Empty, tested.viewStateLiveData.value)
    }

    @Test
    fun `add shape is called on triangle click`() = runBlockingTest {
        tested.onTriangleClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.TRIANGLE)
    }

    @Test
    fun `add shape is called on circle click`() = runBlockingTest {
        tested.onCircleClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.CIRCLE)
    }

    @Test
    fun `add shape is called on square click`() = runBlockingTest {
        tested.onSquareClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.SQUARE)
    }

    @Test
    fun `switch shape is called on shape click`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()

        tested.onShapeClick(shapeDomainEntity)

        verify(switchShape).switchShape(shapeDomainEntity)
    }

    @Test
    fun `delete shape is called on shape long click`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()

        tested.onShapeLongClick(shapeDomainEntity)

        verify(deleteShape).delete(shapeDomainEntity)
    }

    @Test
    fun `undo is called on undo click`() = runBlockingTest {
        tested.onUndoClick()

        verify(undoLastAction).undo()
    }
}
