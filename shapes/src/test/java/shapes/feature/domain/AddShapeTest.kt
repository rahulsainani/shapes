package shapes.feature.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.jupiter.api.Test
import shapes.feature.TestObject.shapeDomainEntity

internal class AddShapeTest {

    private val repository: IShapesRepository = mock()
    private val randomGridPositionGenerator: RandomGridPositionGenerator = mock()
    private val tested = AddShape(repository, randomGridPositionGenerator)

    @Test
    fun `should add shape with the id returned by generator`() {
        val list = listOf(shapeDomainEntity(), shapeDomainEntity(id = 3), shapeDomainEntity(id = 4))
        val id = 2
        val shapeType = ShapeDomainEntity.Type.SQUARE
        val shapeDomainEntity = ShapeDomainEntity(id, shapeType)

        whenever(repository.getAllShapes()).thenReturn(Flowable.just(list))
        whenever(randomGridPositionGenerator.generate(any())).thenReturn(id)
        whenever(repository.addShape(any())).thenReturn(Completable.complete())

        tested
            .addShape(shapeType)
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(repository).addShape(shapeDomainEntity)
    }
}