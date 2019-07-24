package shapes.feature.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class DeleteShapeTest {

    private val repository: IShapesRepository = mock()
    private val tested = DeleteShape(repository)

    @Test
    fun `should call delete on repository`() {
        whenever(repository.delete(any())).thenReturn(Completable.complete())

        val shapeDomainEntity = TestObject.shapeDomainEntity()
        tested
            .delete(shapeDomainEntity)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).delete(shapeDomainEntity)
    }
}