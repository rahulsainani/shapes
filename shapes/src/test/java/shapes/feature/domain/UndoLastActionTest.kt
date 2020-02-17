package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class UndoLastActionTest {

    private val repository: IShapesRepository = mock()
    private val tested = UndoLastAction(repository)

    @Test
    fun `should call undo on repository`() = runBlockingTest {
        tested.undo()
        verify(repository).undo()
    }
}
