package shapes.feature.data

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class ShapeDataMapperTest {

    private val tested = ShapeDataMapper()

    @Test
    fun `should map shape domain entity to data entity`() {
        val domainEntity = TestObject.shapeDomainEntity()
        val expected = TestObject.shapeDataEntity()

        assertEquals(expected, tested.apply(domainEntity))
    }
}