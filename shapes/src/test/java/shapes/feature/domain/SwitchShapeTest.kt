package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import shapes.feature.TestObject.shapeDomainEntity
import shapes.feature.domain.ShapeDomainEntity.Type.CIRCLE
import shapes.feature.domain.ShapeDomainEntity.Type.SQUARE
import shapes.feature.domain.ShapeDomainEntity.Type.TRIANGLE

@ExperimentalCoroutinesApi
internal class SwitchShapeTest {

    private val repository: IShapesRepository = mock()
    private val tested = SwitchShape(repository)

    @Test
    fun `should call update with circle when switching from square`() = runBlockingTest {
        val shapeDomainEntity = shapeDomainEntity(type = SQUARE)
        val expectedDomainEntity = shapeDomainEntity(type = CIRCLE)

        tested.switchShape(shapeDomainEntity)

        verify(repository).updateShape(expectedDomainEntity)
    }

    @Test
    fun `should call update with triangle when switching from circle`() = runBlockingTest {
        val shapeDomainEntity = shapeDomainEntity(type = CIRCLE)
        val expectedDomainEntity = shapeDomainEntity(type = TRIANGLE)

        tested.switchShape(shapeDomainEntity)

        verify(repository).updateShape(expectedDomainEntity)
    }

    @Test
    fun `should call update with square when switching from triangle`() = runBlockingTest {
        val shapeDomainEntity = shapeDomainEntity(type = TRIANGLE)
        val expectedDomainEntity = shapeDomainEntity(type = SQUARE)

        tested.switchShape(shapeDomainEntity)

        verify(repository).updateShape(expectedDomainEntity)
    }
}
