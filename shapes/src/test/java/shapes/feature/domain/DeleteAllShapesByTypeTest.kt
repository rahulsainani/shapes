package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

@ExperimentalCoroutinesApi
internal class DeleteAllShapesByTypeTest {

    private val repository: IShapesRepository = mock()
    private val tested = DeleteAllShapesByType(repository)

    @Test
    fun `should call delete all shapes by type on repository`() = runBlockingTest {
        val type = TestObject.shapeDomainEntity().type
        tested.delete(type)

        verify(repository).deleteAllShapesByType(type)
    }
}
