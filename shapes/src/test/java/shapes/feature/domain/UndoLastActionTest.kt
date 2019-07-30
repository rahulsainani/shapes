package shapes.feature.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.jupiter.api.Test

internal class UndoLastActionTest {

    private val repository: IShapesRepository = mock()
    private val tested = UndoLastAction(repository)

    @Test
    fun `should call undo on repository`() {
        whenever(repository.undo()).thenReturn(Completable.complete())

        tested
            .undo()
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).undo()
    }
}
