package shapes.feature.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shapes.base.data.ShapeDataStack
import shapes.database.ShapeDataEntity
import shapes.database.ShapesDao
import shapes.feature.TestObject
import shapes.test.core.RxPlugins

@ExperimentalCoroutinesApi
@RxPlugins
internal class ShapesRepositoryTest {

    private val shapesDao: ShapesDao = mock()
    private val shapesListDomainMapper: ShapesListDomainMapper = mock()
    private val shapeDataMapper: ShapeDataMapper = mock()
    private val shapeTypeDataMapper: ShapeTypeDataMapper = mock()
    private val stack: ShapeDataStack = mock()

    private val tested =
        ShapesRepository(
            shapesDao,
            shapesListDomainMapper,
            shapeDataMapper,
            shapeTypeDataMapper,
            stack
        )

    @Test
    fun `should return shape domain list on get all shapes`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDomainList = listOf(shapeDomainEntity)
        val shapeDataEntity = TestObject.shapeDataEntity()
        val shapeDataEntityList = listOf(shapeDataEntity)
        val expected = listOf(shapeDomainList)

        val flow = flow { emit(shapeDataEntityList) }

        whenever(shapesDao.getAllShapes()).thenReturn(flow)
        whenever(shapesListDomainMapper.apply(shapeDataEntityList)).thenReturn(shapeDomainList)

        val actual = tested.getAllShapes().toList()

        assertEquals(expected, actual)
        verify(shapesDao).getAllShapes()
    }

    @Test
    fun `should add state to stack and add shape to dao`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesOneShot()).thenReturn(shapeDataEntityList)
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)

        tested.addShape(shapeDomainEntity)

        verify(shapesDao).insert(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and update shape in dao`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesOneShot()).thenReturn(shapeDataEntityList)
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)

        tested.updateShape(shapeDomainEntity)

        verify(shapesDao).update(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and delete shape in dao`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = mock<ShapeDataEntity>()
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesOneShot()).thenReturn(shapeDataEntityList)
        whenever(shapeDataMapper.apply(shapeDomainEntity)).thenReturn(shapeDataEntity)

        tested.delete(shapeDomainEntity)

        verify(shapesDao).delete(shapeDataEntity)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should add state to stack and delete all shapes of type in dao`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()
        val shapeDataEntity = TestObject.shapeDataEntity()
        val shapeDataType = shapeDataEntity.type
        val shapeDataEntityList = listOf(shapeDataEntity)

        whenever(shapesDao.getAllShapesOneShot()).thenReturn(shapeDataEntityList)
        whenever(shapeTypeDataMapper.apply(shapeDomainEntity.type)).thenReturn(shapeDataType)

        tested.deleteAllShapesByType(shapeDomainEntity.type)

        verify(shapesDao).deleteAllShapesByType(shapeDataType)
        verify(stack).push(shapeDataEntityList)
    }

    @Test
    fun `should throw NoSuchElementException when stack pop returns null on undo`() =
        runBlockingTest {
            whenever(stack.pop()).thenReturn(null)
            assertThrows<NoSuchElementException> { runBlockingTest { tested.undo() } }

            verify(shapesDao, never()).deleteAndInsertInTransaction(any())
        }

    @Test
    fun `should update dao with the result of stack pop on undo`() =
        runBlockingTest {
            val topOfTheStack = listOf<ShapeDataEntity>()

            whenever(stack.pop()).thenReturn(topOfTheStack)

            tested.undo()

            verify(shapesDao).deleteAndInsertInTransaction(topOfTheStack)
        }
}
