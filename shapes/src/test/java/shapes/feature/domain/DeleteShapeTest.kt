package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import shapes.feature.TestObject

@ExperimentalCoroutinesApi
internal class DeleteShapeTest {

    private val repository: IShapesRepository = mock()
    private val tested = DeleteShape(repository)

    @Test
    fun `should call delete on repository`() = runBlockingTest {
        val shapeDomainEntity = TestObject.shapeDomainEntity()

        tested.delete(shapeDomainEntity)

        verify(repository).delete(shapeDomainEntity)
    }
}
