package shapes.feature.presentation.editor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.BehaviorSubject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shapes.base.rx.SchedulerProvider
import shapes.feature.TestObject
import shapes.feature.domain.AddShape
import shapes.feature.domain.DeleteShape
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.SwitchShape
import shapes.feature.domain.UndoLastAction
import shapes.test.core.InstantTask
import java.util.concurrent.TimeUnit

@InstantTask
internal class ShapesEditorViewModelTest {

    private val schedulerProvider: SchedulerProvider = mock()
    private val retrieveShapes: RetrieveShapes = mock()
    private val addShape: AddShape = mock()
    private val switchShape: SwitchShape = mock()
    private val deleteShape: DeleteShape = mock()
    private val undoLastAction: UndoLastAction = mock()

    private lateinit var tested: ShapesEditorViewModel

    private val shapesSteam = BehaviorSubject.create<List<ShapeDomainEntity>>()
    private val testScheduler = TestScheduler()

    @BeforeEach
    fun setup() {
        whenever(retrieveShapes.retrieveShapes())
            .thenReturn(shapesSteam.toFlowable(BackpressureStrategy.LATEST))
        whenever(schedulerProvider.computation()).thenReturn(testScheduler)
        tested = ShapesEditorViewModel(
            retrieveShapes, addShape, switchShape, deleteShape, undoLastAction, schedulerProvider
        )
    }

    @Test
    fun `shapes list content is posted to live data`() {
        val shapesList = listOf<ShapeDomainEntity>(mock(), mock())
        shapesSteam.onNext(shapesList)

        testScheduler.advanceTimeBy(50, TimeUnit.MILLISECONDS)

        assertEquals(ShapesEditorViewState.Content(shapesList), tested.viewStateLiveData.value)
    }

    @Test
    fun `empty state is posted to live data`() {
        val shapesList = emptyList<ShapeDomainEntity>()
        shapesSteam.onNext(shapesList)

        testScheduler.advanceTimeBy(50, TimeUnit.MILLISECONDS)

        assertEquals(ShapesEditorViewState.Empty, tested.viewStateLiveData.value)
    }

    @Test
    fun `add shape is called on triangle click`() {
        whenever(addShape.addShape(any())).thenReturn(Completable.complete())

        tested.onTriangleClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.TRIANGLE)
    }

    @Test
    fun `add shape is called on circle click`() {
        whenever(addShape.addShape(any())).thenReturn(Completable.complete())

        tested.onCircleClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.CIRCLE)
    }

    @Test
    fun `add shape is called on square click`() {
        whenever(addShape.addShape(any())).thenReturn(Completable.complete())

        tested.onSquareClick()

        verify(addShape).addShape(ShapeDomainEntity.Type.SQUARE)
    }

    @Test
    fun `switch shape is called on shape click`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        whenever(switchShape.switchShape(any())).thenReturn(Completable.complete())

        tested.onShapeClick(shapeDomainEntity)

        verify(switchShape).switchShape(shapeDomainEntity)
    }

    @Test
    fun `delete shape is called on shape long click`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        whenever(deleteShape.delete(any())).thenReturn(Completable.complete())

        tested.onShapeLongClick(shapeDomainEntity)

        verify(deleteShape).delete(shapeDomainEntity)
    }

    @Test
    fun `undo is called on undo click`() {
        whenever(undoLastAction.undo()).thenReturn(Completable.complete())

        tested.onUndoClick()

        verify(undoLastAction).undo()
    }
}
