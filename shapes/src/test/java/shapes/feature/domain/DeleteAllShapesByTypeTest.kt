package shapes.feature.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

internal class DeleteAllShapesByTypeTest {

    private val repository: IShapesRepository = mock()
    private val tested = DeleteAllShapesByType(repository)

    @Test
    fun `should call delete all shapes by type on repository`() {
        whenever(repository.deleteAllShapesByType(any())).thenReturn(Completable.complete())

        val type = TestObject.shapeDomainEntity().type
        tested
            .delete(type)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).deleteAllShapesByType(type)
    }
}