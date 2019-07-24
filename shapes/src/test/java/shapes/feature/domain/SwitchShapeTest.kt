package shapes.feature.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import shapes.feature.TestObject.shapeDomainEntity
import shapes.feature.domain.ShapeDomainEntity.Type.*

internal class SwitchShapeTest {

    private val repository: IShapesRepository = mock()
    private val tested = SwitchShape(repository)

    @BeforeEach
    fun setup() {
        whenever(repository.updateShape(any())).thenReturn(Completable.complete())
    }

    @Test
    fun `should call update with circle when switching from square`() {
        val shapeDomainEntity = shapeDomainEntity(type = SQUARE)
        val expectedDomainEntity = shapeDomainEntity(type = CIRCLE)

        tested
            .switchShape(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).updateShape(expectedDomainEntity)
    }

    @Test
    fun `should call update with triangle when switching from circle`() {
        val shapeDomainEntity = shapeDomainEntity(type = CIRCLE)
        val expectedDomainEntity = shapeDomainEntity(type = TRIANGLE)

        tested
            .switchShape(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).updateShape(expectedDomainEntity)
    }

    @Test
    fun `should call update with square when switching from triangle`() {
        val shapeDomainEntity = shapeDomainEntity(type = TRIANGLE)
        val expectedDomainEntity = shapeDomainEntity(type = SQUARE)

        tested
            .switchShape(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).updateShape(expectedDomainEntity)
    }
}