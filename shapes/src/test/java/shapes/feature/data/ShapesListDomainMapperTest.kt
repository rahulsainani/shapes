package shapes.feature.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class ShapesListDomainMapperTest {

    private val shapesDomainMapper: ShapeDomainMapper = mock()
    private val tested = ShapesListDomainMapper(shapesDomainMapper)

    @Test
    fun `should map list of shape data entities to list of domain entities`() {
        val listDataEntities = listOf(TestObject.shapeDataEntity())

        val shapeDomainEntity = TestObject.shapeDomainEntity()
        whenever(shapesDomainMapper.apply(any())).thenReturn(shapeDomainEntity)

        assertEquals(listOf(shapeDomainEntity), tested.apply(listDataEntities))
    }
}