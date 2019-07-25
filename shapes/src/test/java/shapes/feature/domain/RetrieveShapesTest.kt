package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject
import org.junit.jupiter.api.Test

internal class RetrieveShapesTest {

    private val repository: IShapesRepository = mock()
    private val tested = RetrieveShapes(repository)

    private val shapesSteam = BehaviorSubject.create<List<ShapeDomainEntity>>()

    @Test
    fun `should call get all shapes on repository`() {
        val mock = mock<List<ShapeDomainEntity>>()
        whenever(repository.getAllShapes()).thenReturn(shapesSteam.toFlowable(BackpressureStrategy.LATEST))

        shapesSteam.onNext(mock)

        tested
            .retrieveShapes()
            .test()
            .assertValue(mock)
            .assertNoErrors()
            .assertNotComplete()

        verify(repository).getAllShapes()
    }
}