package shapes.feature.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import shapes.feature.TestObject.shapeDomainEntity

@ExperimentalCoroutinesApi
internal class AddShapeTest {

    private val repository: IShapesRepository = mock()
    private val randomGridPositionGenerator: RandomGridPositionGenerator = mock()
    private val tested = AddShape(repository, randomGridPositionGenerator)

    @Test
    fun `should add shape with the id returned by generator`() = runBlockingTest {
        val list = listOf(shapeDomainEntity(), shapeDomainEntity(id = 3), shapeDomainEntity(id = 4))
        val id = 2
        val shapeType = ShapeDomainEntity.Type.SQUARE
        val shapeDomainEntity = ShapeDomainEntity(id, shapeType)

        whenever(repository.getAllShapesOneShot()).thenReturn(list)
        whenever(randomGridPositionGenerator.generate(any())).thenReturn(id)

        tested.addShape(shapeType)

        verify(repository).addShape(shapeDomainEntity)
    }
}
