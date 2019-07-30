package shapes.feature.presentation.stats

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shapes.base.presentation.StringsProvider
import shapes.feature.R
import shapes.feature.TestObject.shapeDomainEntity
import shapes.feature.domain.ShapeDomainEntity.Type.*

internal class StatisticsViewEntityMapperTest {

    private val stringsProvider: StringsProvider = mock()
    private val tested = StatisticsViewEntityMapper(stringsProvider)

    @Test
    fun `should map to statistics item entity list`() {
        whenever(stringsProvider.getString(any())).thenAnswer { it.arguments.first().toString() }

        val domainList = listOf(
            shapeDomainEntity(id = 1, type = CIRCLE),
            shapeDomainEntity(id = 2, type = TRIANGLE),
            shapeDomainEntity(id = 4, type = TRIANGLE),
            shapeDomainEntity(id = 5, type = TRIANGLE),
            shapeDomainEntity(id = 3, type = SQUARE),
            shapeDomainEntity(id = 6, type = SQUARE)
        )

        val expected = listOf(
            StatisticsItemEntity(R.string.circle.toString(), "1", CIRCLE),
            StatisticsItemEntity(R.string.triangle.toString(), "3", TRIANGLE),
            StatisticsItemEntity(R.string.square.toString(), "2", SQUARE)
        )

        assertEquals(expected, tested.apply(domainList))
    }
}