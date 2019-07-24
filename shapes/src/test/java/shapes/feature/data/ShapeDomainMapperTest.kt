package shapes.feature.data

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class ShapeDomainMapperTest {

    private val tested = ShapeDomainMapper()

    @Test
    fun `should map shape data entity to domain entity`() {
        val dataEntity = TestObject.shapeDataEntity()
        val expected = TestObject.shapeDomainEntity()

        assertEquals(expected, tested.apply(dataEntity))
    }
}