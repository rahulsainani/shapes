package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class RetrieveShapesTest {

    private val repository: IShapesRepository = mock()
    private val tested = RetrieveShapes(repository)

    @Test
    fun `should call get all shapes on repository`() = runBlockingTest {
        val firstEmission = mock<List<ShapeDomainEntity>>()
        val secondEmission = mock<List<ShapeDomainEntity>>()
        val expected = listOf(firstEmission, secondEmission)

        val flow = flow {
            emit(firstEmission)
            emit(secondEmission)
        }

        whenever(repository.getAllShapes()).thenReturn(flow)

        val actual = tested.retrieveShapes().toList()

        assertEquals(expected, actual)
        verify(repository).getAllShapes()
    }
}
