package shapes.feature.presentation.stats

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.subjects.BehaviorSubject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shapes.feature.domain.DeleteAllShapesByType
import shapes.feature.domain.RetrieveShapes
import shapes.feature.domain.ShapeDomainEntity
import shapes.test.core.InstantTask

@InstantTask
internal class StatisticsViewModelTest {

    private val retrieveShapes: RetrieveShapes = mock()
    private val deleteAllShapesByType: DeleteAllShapesByType = mock()
    private val statisticsViewEntityMapper: StatisticsViewEntityMapper = mock()

    private val shapesSteam = BehaviorSubject.create<List<ShapeDomainEntity>>()

    private lateinit var tested: StatisticsViewModel

    private val shapesList = mock<List<ShapeDomainEntity>>()
    private val statItemEntities = mock<List<StatisticsItemEntity>>()

    @BeforeEach
    fun setup() {
        whenever(retrieveShapes.retrieveShapes())
            .thenReturn(shapesSteam.toFlowable(BackpressureStrategy.LATEST))

        tested = StatisticsViewModel(
            retrieveShapes, deleteAllShapesByType, statisticsViewEntityMapper
        )
    }

    @Test
    fun `number of shapes is posted on live data`() {
        whenever(statisticsViewEntityMapper.apply(shapesList)).thenReturn(statItemEntities)

        shapesSteam.onNext(shapesList)

        Assertions.assertEquals(statItemEntities, tested.statsListLiveData.value)
    }

    @Test
    fun `delete all shapes of type is called on item click`() {
        val shapeType = ShapeDomainEntity.Type.TRIANGLE
        whenever(deleteAllShapesByType.delete(any())).thenReturn(Completable.complete())

        tested.onItemClick(shapeType)

        verify(deleteAllShapesByType).delete(shapeType)
    }
}
