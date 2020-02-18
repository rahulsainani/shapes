package shapes.feature.presentation.stats

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shapes.feature.domain.DeleteAllShapesByType
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.test.core.CoroutinesExtension
import shapes.test.core.InstantTask

@ExperimentalCoroutinesApi
@InstantTask
@CoroutinesExtension
internal class StatisticsViewModelTest {

    private val retrieveShapes: RetrieveShapes = mock()
    private val deleteAllShapesByType: DeleteAllShapesByType = mock()
    private val statisticsViewEntityMapper: StatisticsViewEntityMapper = mock()

    private lateinit var tested: StatisticsViewModel

    private val shapesList = mock<List<ShapeDomainEntity>>()

    private fun init() {
        tested = StatisticsViewModel(
            retrieveShapes, deleteAllShapesByType, statisticsViewEntityMapper
        )
    }

    private fun initWithFlow() {
        whenever(retrieveShapes.retrieveShapes()).thenReturn(flowOf(shapesList))
        init()
    }

    @Test
    fun `number of shapes is posted on live data`() = runBlockingTest {
        val statItemEntities: List<StatisticsItemEntity> = listOf(mock(), mock())
        whenever(statisticsViewEntityMapper.apply(shapesList)).thenReturn(statItemEntities)

        initWithFlow()

        assertEquals(
            StatisticsViewState.Content(statItemEntities),
            tested.statsViewStateLiveData.value
        )
    }

    @Test
    fun `empty is posted on live data`() = runBlockingTest {
        val statItemEntities = emptyList<StatisticsItemEntity>()
        whenever(statisticsViewEntityMapper.apply(shapesList)).thenReturn(statItemEntities)
        initWithFlow()

        assertEquals(StatisticsViewState.Empty, tested.statsViewStateLiveData.value)
    }

    @Test
    fun `delete all shapes of type is called on item click`() = runBlockingTest {
        init()
        val shapeType = ShapeDomainEntity.Type.TRIANGLE

        tested.onItemClick(shapeType)

        verify(deleteAllShapesByType).delete(shapeType)
    }
}
