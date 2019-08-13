package shapes.feature.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import org.junit.jupiter.api.Test
import shapes.base.data.ShapeDataStack
import shapes.base.database.ShapeDataEntity
import shapes.base.database.ShapesDao
import shapes.feature.TestObject
import shapes.test.core.RxPlugins

@RxPlugins
internal class ShapesRepositoryTest {

    private val shapesDao: ShapesDao = mock()
    private val shapesListDomainMapper: ShapesListDomainMapper = mock()
    private val shapeDataMapper: ShapeDataMapper = mock()
    private val shapeTypeDataMapper: ShapeTypeDataMapper = mock()
    private val stack: ShapeDataStack = mock()

    private val stream = BehaviorSubject.create<List<ShapeDataEntity>>()

    private val tested =
        ShapesRepository(
            shapesDao,
            shapesListDomainMapper,
            shapeDataMapper,
            shapeTypeDataMapper,
            stack
        )

    @Test
    fun `should return shape domain list on get all shapes`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDomainList = listOf(shapeDomainEntity)
        val shapeDataEntity = TestObject.shapeDataEntity()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapes())
            .thenReturn(stream.toFlowable(BackpressureStrategy.LATEST))
        whenever(shapesListDomainMapper.apply(shapeDataEntityList)).thenReturn(shapeDomainList)

        stream.onNext(shapeDataEntityList)

        tested
            .getAllShapes()
            .test()
            .assertValue(shapeDomainList)
            .assertNotComplete()
            .assertNoErrors()

        verify(shapesDao).getAllShapes()
    }

    @Test
    fun `should add state to stack and add shape to dao`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesSingle()).thenReturn(Single.just(shapeDataEntityList))
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)
        whenever(shapesDao.insert(shapeDataEntity)).thenReturn(Completable.complete())

        tested
            .addShape(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(shapesDao).insert(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and update shape in dao`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesSingle()).thenReturn(Single.just(shapeDataEntityList))
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)
        whenever(shapesDao.update(shapeDataEntity)).thenReturn(Completable.complete())

        tested
            .updateShape(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(shapesDao).update(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and delete shape in dao`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesSingle()).thenReturn(Single.just(shapeDataEntityList))
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)
        whenever(shapesDao.delete(shapeDataEntity)).thenReturn(Completable.complete())

        tested
            .delete(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(shapesDao).delete(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and delete all shapes of type in dao`() {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = TestObject.shapeDataEntity()
        val shapeDataType = shapeDataEntity.type
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesSingle()).thenReturn(Single.just(shapeDataEntityList))
        whenever(shapeTypeDataMapper.apply(shapeDomainEntity.type)).thenReturn(shapeDataType)
        whenever(shapesDao.deleteAllShapesByType(any())).thenReturn(Completable.complete())

        tested
            .deleteAllShapesByType(shapeDomainEntity.type)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(shapesDao).deleteAllShapesByType(shapeDataType)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should throw NoSuchElementException when stack pop returns null on undo`() {
        whenever(stack.pop()).thenReturn(null)

        tested
            .undo()
            .test()
            .assertNoValues()
            .assertError(NoSuchElementException::class.java)

        verify(shapesDao, never()).deleteAndInsertInTransaction(any())
    }

    @Test
    fun `should update dao with the result of stack pop on undo`() {
        val topOfTheStack = listOf<ShapeDataEntity>()

        whenever(stack.pop()).thenReturn(topOfTheStack)

        tested
            .undo()
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(shapesDao).deleteAndInsertInTransaction(topOfTheStack)
    }
}
