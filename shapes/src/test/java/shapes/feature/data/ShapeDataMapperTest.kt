package shapes.feature.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class ShapeDataMapperTest {

    private val shapeTypeDataMapper: ShapeTypeDataMapper = mock()
    private val tested = ShapeDataMapper(shapeTypeDataMapper)

    @Test
    fun `should map shape domain entity to data entity`() {
        val domainEntity = TestObject.shapeDomainEntity()
        val expected = TestObject.shapeDataEntity()

        whenever(shapeTypeDataMapper.apply(domainEntity.type)).thenReturn(expected.type)

        assertEquals(expected, tested.apply(domainEntity))
    }
}
