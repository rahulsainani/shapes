package shapes.feature.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class ShapeTypeDataMapperTest {

    private val tested = ShapeTypeDataMapper()

    @Test
    fun `should map domain shape type to data shape type`() {
        val domainEntity = TestObject.shapeDomainEntity().type
        val expected = TestObject.shapeDataEntity().type

        Assertions.assertEquals(expected, tested.apply(domainEntity))
    }
}